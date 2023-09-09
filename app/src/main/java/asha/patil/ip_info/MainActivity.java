package asha.patil.ip_info;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button butncheck, getmore;
    TextView t1,t2,t3,t4,t5,t6,t7,t8;
    EditText txtJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butncheck=findViewById(R.id.button);
        getmore=findViewById(R.id.button0);
        txtJson=findViewById(R.id.checkip);
        t1=findViewById(R.id.tv1);
        t2=findViewById(R.id.tv2);
        t3=findViewById(R.id.tv3);
        t4=findViewById(R.id.tv4);
        t5=findViewById(R.id.tv5);
        t6=findViewById(R.id.tv6);
        t7=findViewById(R.id.tv7);
        t8=findViewById(R.id.tv8);

        getmore.setEnabled(false);
        butncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { jsonfetcher();
            }
        });
    }

    private void jsonfetcher() {
        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.ipify.org/?format=json", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    txtJson.setText(response.getString("ip"));
                    if (txtJson.getText().toString() != "check IP") {
                        getmore.setEnabled(true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("My app", "Something Went Wrong");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void moreinfo(View view) {
        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,"https://ipinfo.io/"+txtJson.getText().toString()+"/geo", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    t1.setText(response.getString("ip"));
                    t2.setText(response.getString("city"));
                    t3.setText(response.getString("region"));
                    t4.setText(response.getString("country"));
                    t5.setText(response.getString("loc"));
                    t6.setText(response.getString("org"));
                    t7.setText(response.getString("postal"));
                    t8.setText(response.getString("timezone"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("My app", "Something Went Wrong");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}