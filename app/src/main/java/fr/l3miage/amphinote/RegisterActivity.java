package fr.l3miage.amphinote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
    }

    final EditText Lastname = (EditText) findViewById(R.id.Lastname);
    final EditText Username = (EditText) findViewById(R.id.Username);
    final EditText Email = (EditText) findViewById(R.id.Email);
    final EditText Age = (EditText) findViewById(R.id.Age);
    final EditText Password = (EditText) findViewById(R.id.Password);
    final Button Register = (EditText) findViewById(R.id.Register);
}
