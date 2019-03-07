package fr.l3miage.amphinote;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import fr.l3miage.amphinote.databinding.LoginActivityBinding;
import fr.l3miage.amphinote.model.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    LoginActivityBinding login_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        login_binding = DataBindingUtil.setContentView(this, R.layout.login_activity);

        login_binding.createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        login_binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authentication();
            }
        });
    }

    protected void authentication(){

        String usermail = login_binding.usermail.getText().toString();
        String password = login_binding.userpassword.getText().toString();
        String base = usermail + ":" +password;
        String authHeader = "Basic "+ Base64.encodeToString(base.getBytes(),Base64.NO_WRAP);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.201/amphinote/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AmphinoteApi amphinoteApi = retrofit.create(AmphinoteApi.class);

        Call<UserModel> call = amphinoteApi.getUserdata(authHeader,usermail);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this,"Mauvaise Combinaison",Toast.LENGTH_SHORT).show();
                    return;
                }
                UserModel User = response.body();
                Toast.makeText(LoginActivity.this,"Bonjour "+ User.getUsername() ,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, UserAreaActivity.class));
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Serveur inaccessible"+t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }

}

