package co.timetrax.seisproject;

/**
 * Created by mpan0590 on 4/19/2016.
 */
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://timetrax.co/RegisterTwo.php";//use RegisterTwo.php
    private Map<String, String> params;

    public RegisterRequest(String name, int pin,  int bal, String username, String password, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("pin", pin + "");
        params.put("bal", bal + "");
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
