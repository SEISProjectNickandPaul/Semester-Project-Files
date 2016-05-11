package co.timetrax.seisproject;

import android.content.Intent;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class TransActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        final TextView sender = (TextView)findViewById(R.id.sender);
        sender.setText(username);

        final String saction = intent.getStringExtra("action");
        final TextView action = (TextView)findViewById(R.id.action);
        action.setText(saction);


        final String amount = intent.getStringExtra("amount");
        final TextView input_amount = (TextView)findViewById(R.id.input_amount);
        input_amount.setText(amount);

        final EditText rpin = (EditText)findViewById(R.id.rpin);

        final Button btn_send = (Button)findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final  int points = Integer.parseInt(input_amount.getText().toString());
                final String username = sender.getText().toString();
                final String phonenumber  = rpin.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                int bal = jsonResponse.getInt("bal");
                                String signedUser = jsonResponse.getString("username");
                                Intent intent = new Intent(TransActivity.this, MainActivity.class);
                                intent.putExtra("bal", bal);
                                intent.putExtra("signedUser", signedUser);
                                TransActivity.this.startActivity(intent);
                                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                TransRequest transRequest = new TransRequest(username, points, phonenumber, responseListener);
                RequestQueue queue = Volley.newRequestQueue(TransActivity.this);
                queue.add(transRequest);
            }
        });
    }
}
