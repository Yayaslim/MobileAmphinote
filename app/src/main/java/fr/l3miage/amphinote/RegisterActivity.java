package fr.l3miage.amphinote;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        final EditText etusername = findViewById(R.id.editText2);
        final EditText etlastname = findViewById(R.id.editText);
        final EditText etage = findViewById(R.id.editText3);
        final EditText etpassword = findViewById(R.id.editText4);
        final EditText etemail = findViewById(R.id.editText5);
        final Button bRegister = findViewById(R.id.button);
        bRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String username = etusername.getText().toString();
                final String lastname = etlastname.getText().toString();
                final String email = etemail.getText().toString();
                final int age = Integer.parseInt(etage.getText().toString());
                final String password = etpassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
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

                RegisterRequest registerRequest = new RegisterRequest(username, lastname, email, age, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }

}