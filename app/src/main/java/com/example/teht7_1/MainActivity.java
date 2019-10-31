package com.example.teht7_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RequestQueue jono;
    private JsonArrayRequest request;
    private OmaAdapteri adapteri;
    private String imageURLString = "https://loremflickr.com/320/240/";
    private String jsonURLString = "https://loremflickr.com/json/g/320/240/";
    private Bitmap response1;
    // https://www.youtube.com/watch?v=HRPpQw0dzko
    // android volley image request exmaple
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar t = (Toolbar)findViewById(R.id.tool);

        Button btn = new Button(this);
        Toolbar.LayoutParams l1 = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        l1.gravity = Gravity.START;
        btn.setLayoutParams(l1);
        btn.setText("Get");
        t.addView(btn);

        final EditText text = new EditText(this);
        Toolbar.LayoutParams l2 = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        l2.gravity = Gravity.CENTER;
        text.setLayoutParams(l2);
        t.addView(text);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String tieto = text.getText().toString();
                if (tieto.matches("")) {
                    finish();
                } else {
                    request = new JsonArrayRequest(
                            jsonURLString + tieto,
                            new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    GsonBuilder gsonBuilder = new GsonBuilder();
                                    Gson gson = gsonBuilder.create();
                                    Type listantyyppi = new TypeToken<ArrayList<String>>() {
                                    }.getType();
                                    ArrayList<String> lista;
                                    lista = gson.fromJson(response.toString(), listantyyppi);
                                    adapteri.addAll(lista);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                    jono.add(request);
                    ImageRequest imageRequest = new ImageRequest(
                            imageURLString,
                            new Response.Listener<Bitmap>() {
                                @Override
                                public void onResponse(Bitmap response) {
                                    response1 = response;
                                    adapteri.add(response);
                                }
                            },
                            0,
                            0,
                            new Response.ErrorListener() { // Error listener
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                    Snackbar.make(mCLayout,"Error",Snackbar.LENGTH_LONG).show();
                                    //Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                                }
                            }
                    );

                    // Add ImageRequest to the RequestQueue
                    jono.add(imageRequest);
                }
            }
        });
    }

}
