package fr.l3miage.amphinote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        final EditText Email = (EditText) findViewById(R.id.Email);
        final EditText Password = (EditText) findViewById(R.id.Password);
        final Button Blogin = (EditText) findViewById(R.id.Blogin);
        final Button BRegister = (EditText) findViewById(R.id.BRegister);

        BRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
    }
}
