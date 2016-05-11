package co.timetrax.seisproject;

import android.app.AlertDialog;
import android.content.Intent;
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

public class CreateUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        final EditText input_fname = (EditText)findViewById(R.id.input_fname);
        final EditText input_lname = (EditText)findViewById(R.id.input_lname);
        final EditText input_username = (EditText)findViewById(R.id.input_username);
        final EditText input_password = (EditText)findViewById(R.id.input_password);
        final EditText input_phonenumber = (EditText)findViewById(R.id.input_phonenumber);

        final Button btn_signup = (Button)findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = input_fname.getText().toString() + " " + input_lname.getText().toString();
                final String username = input_username.getText().toString();
                final int pin = Integer.parseInt(input_phonenumber.getText().toString());
                final int bal = (1 * 100);
                final String password = input_password.getText().toString();
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
                RegisterRequest registerRequest = new RegisterRequest(name, pin, bal,  username,  password, responseListener);
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
