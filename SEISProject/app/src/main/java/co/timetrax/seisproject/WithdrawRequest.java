package co.timetrax.seisproject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by mpan0590 on 4/25/2016.
 */
public class WithdrawRequest extends StringRequest {

    private static final String WITHDRAW_REQUEST_URL = "http://timetrax.co/Withdraw.php";//consider hashing the passwords
    private Map<String, String> params;

    public WithdrawRequest(String username, int points,  Response.Listener<String> listener) {
        super(Request.Method.POST, WITHDRAW_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("points", points + "");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
