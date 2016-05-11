package co.timetrax.seisproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_create_user);

        final EditText input_fname = (EditText)findViewById(R.id.input_fname);
        final EditText input_lname = (EditText)findViewById(R.id.input_lname);
        final EditText input_username = (EditText)findViewById(R.id.input_username);
        final EditText input_password = (EditText)findViewById(R.id.input_password);
        final EditText input_phonenumber = (EditText)findViewById(R.id.input_phonenumber);
        final EditText input_email = (EditText)findViewById(R.id.input_email);
        final Button btn_signup = (Button)findViewById(R.id.btn_signup);

        final Button btn_next = (Button)findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next.setVisibility(View.GONE);
                input_email.setVisibility(View.VISIBLE);
                input_phonenumber.setVisibility(View.VISIBLE);
                input_fname.setVisibility(View.GONE);
                input_lname.setVisibility(View.GONE);
                input_password.setVisibility(View.GONE);
                input_username.setVisibility(View.GONE);
                btn_signup.setVisibility(View.VISIBLE);
            }
        });


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = input_fname.getText().toString() + " " + input_lname.getText().toString();
                final String username = input_username.getText().toString();
                final String phonenumber = input_phonenumber.getText().toString();
                final int bal = (1 * 100);
                final String password = input_password.getText().toString();
                final String email = input_email.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(CreateUser.this, UserLogin.class);
                                CreateUser.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(CreateUser.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


                //creating user accounts
                RegisterRequest registerRequest = new RegisterRequest(name, email, username,  password, phonenumber, bal,  responseListener);
                RequestQueue queue = Volley.newRequestQueue(CreateUser.this);
                queue.add(registerRequest);


            }
        });
        final TextView link_login = (TextView)findViewById(R.id.link_login);
        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateUser.this, UserLogin.class);
                CreateUser.this.startActivity(intent);
            }
        });

    }
}
