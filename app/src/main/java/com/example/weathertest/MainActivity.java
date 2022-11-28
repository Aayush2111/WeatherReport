package com.example.weathertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button btn_cityID, btn_getWeatherByID, btn_getWeatherByName;
    EditText dataInput;
    ListView weatherReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cityID =  findViewById(R.id.cityID);
        btn_getWeatherByID = findViewById(R.id.weatherID);
        btn_getWeatherByName = findViewById(R.id.nameID);

        dataInput = findViewById(R.id.et_data);
        weatherReport = findViewById(R.id.reports);


        //click Listeners
        btn_getWeatherByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"you clicked me",Toast.LENGTH_SHORT).show();
            }
        });

        btn_getWeatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"you typed " + dataInput.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        btn_cityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                // Instantiate the RequestQueue.
//                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                String url = "https://api.weatherapi.com/v1/current.json?key=e75cbcbac750483c9a0205805221608&q="+ dataInput.getText().toString() + "&aqi=no";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {
                    @Override

                    public void onResponse(JSONObject response) {


                        String temp = "";
                        try {
                            temp = response.getJSONObject("current").getString("temp_c");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(MainActivity.this,"temp is: " + temp ,Toast.LENGTH_SHORT).show();
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Please enter city name", Toast.LENGTH_SHORT).show();

                            }
                         });

                // Add a request (in this example, called stringRequest) to your RequestQueue.
                MySingleton.getInstance(MainActivity.this).addToRequestQueue(request);
//                queue.add(request);

            }
        });


    }
}