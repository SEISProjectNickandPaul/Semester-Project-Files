package co.timetrax.seisproject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by mpan0590 on 4/25/2016.
 */
public class DepositRequest extends StringRequest {

    private static final String DEPOSIT_REQUEST_URL = "http://timetrax.co/Deposit.php";//consider hashing the passwords
    private Map<String, String> params;

    public DepositRequest(String username, int points,  Response.Listener<String> listener) {
        super(Request.Method.POST, DEPOSIT_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("points", points + "");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
