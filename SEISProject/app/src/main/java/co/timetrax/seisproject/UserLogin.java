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

public class UserLogin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        final EditText input_username = (EditText)findViewById(R.id.input_username);
        final EditText input_password = (EditText)findViewById(R.id.input_password);

        final Button btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = input_username.getText().toString();
                final String password = input_password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                int bal = jsonResponse.getInt("bal");
                                String signedUser = jsonResponse.getString("username");
                                Intent intent = new Intent(UserLogin.this, MainActivity.class);
                                intent.putExtra("bal", bal);
                                intent.putExtra("signedUser", signedUser);
                                UserLogin.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserLogin.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(UserLogin.this);
                queue.add(loginRequest);
                Intent intent = new Intent(UserLogin.this, MainActivity.class);
                UserLogin.this.startActivity(intent);

                //Intent intent = new Intent(UserLogin.this, MainActivity.class);
                //UserLogin.this.startActivity(intent);
            }
        });
        final TextView link_signup = (TextView)findViewById(R.id.link_signup);
        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLogin.this, CreateUser.class);
                UserLogin.this.startActivity(intent);
            }
        });


    }
}
