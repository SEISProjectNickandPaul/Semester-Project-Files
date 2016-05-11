package co.timetrax.seisproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.blackcat.currencyedittext.CurrencyEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    final Context context = this;
    private Button button;
    private EditText result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        final String eUsername = intent.getStringExtra("signedUser");
        final TextView etUsername = (TextView)findViewById(R.id.etUsername);
        etUsername.setText(eUsername);

        final int etBal = intent.getIntExtra("bal", -1);
      //  final  String etBal = intent.getStringExtra("bal");
        final TextView eBal = (TextView)findViewById(R.id.etBal);
        final TextView balnote = (TextView) findViewById(R.id.balnote);
        balnote.setText("you have" + " " + etBal + " "+ "available");
        eBal.setText(etBal + "");

        final TextView input_amount = (TextView)findViewById(R.id.input_amount);

        final TextView link_send = (TextView)findViewById(R.id.link_send);
        link_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String action = "send";
                String username = etUsername.getText().toString();
                String amount = input_amount.getText().toString();
                int a = Integer.parseInt(input_amount.getText().toString());
                int bal = Integer.parseInt(eBal.getText().toString());
                if (a <= bal ){

                    Intent transintent = new Intent(MainActivity.this, TransActivity.class);
                    transintent.putExtra("action", action);
                    transintent.putExtra("amount", amount);
                    transintent.putExtra("username", username);
                    MainActivity.this.startActivity(transintent);


                }else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("You Don't Have Enough Funds!")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();

                }
            }
        });

        final TextView link_request = (TextView)findViewById(R.id.link_request);
        link_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String action = "request";
                Intent transintent = new Intent(MainActivity.this, TransActivity.class);
                MainActivity.this.startActivity(transintent);
            }
        });

        result = (EditText)findViewById(R.id.editTextResult);
        final Button btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompts, null);

                android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Deposit",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                       // result.setText(userInput.getText());
                                       // result.setVisibility(View.VISIBLE);
                                        final String username = etUsername.getText().toString();
                                        final int points = Integer.parseInt(userInput.getText().toString());
                                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                try {
                                                    JSONObject jsonResponse = new JSONObject(response);
                                                    boolean success = jsonResponse.getBoolean("success");
                                                    if (success) {
                                                        int bal = jsonResponse.getInt("bal");
                                                        String signedUser = jsonResponse.getString("username");
                                                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                                        intent.putExtra("bal", bal);
                                                        intent.putExtra("signedUser", signedUser);
                                                        MainActivity.this.startActivity(intent);
                                                        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        };

                                        DepositRequest depositRequest = new DepositRequest(username, points, responseListener);
                                        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                                        queue.add(depositRequest);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });

        final Button btn_with = (Button)findViewById(R.id.btn_with);
        btn_with.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompts, null);

                android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Withdraw",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        final String username = etUsername.getText().toString();
                                        final int points = Integer.parseInt(userInput.getText().toString());
                                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                try {
                                                    JSONObject jsonResponse = new JSONObject(response);
                                                    boolean success = jsonResponse.getBoolean("success");
                                                    if (success) {
                                                        int bal = jsonResponse.getInt("bal");
                                                        String signedUser = jsonResponse.getString("username");
                                                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                                        intent.putExtra("bal", bal);
                                                        intent.putExtra("signedUser", signedUser);
                                                        MainActivity.this.startActivity(intent);
                                                        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        };

                                        WithdrawRequest withdrawRequest = new WithdrawRequest(username, points, responseListener);
                                        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                                        queue.add(withdrawRequest);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


            }
        });
    }
}
