package co.timetrax.seisproject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mpan0590 on 4/20/2016.
 */
public class TransRequest extends StringRequest{

    private static final String TRANS_REQUEST_URL = "http://timetrax.co/Needed.php";//consider hashing the passwords
    private Map<String, String> params;

    public TransRequest(String username, int points, int pin,  Response.Listener<String> listener) {
        super(Request.Method.POST, TRANS_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);

        params.put("points", points + "");
        params.put("pin", pin + "");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
