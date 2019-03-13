package fr.l3miage.amphinote;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import fr.l3miage.amphinote.databinding.LoginActivityBinding;
import fr.l3miage.amphinote.model.UserModel;
import fr.l3miage.amphinote.utils.Serveur;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private LoginActivityBinding login_binding;

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

        UserModel userModel= new UserModel();
        userModel.setEmail(login_binding.usermail.getText().toString());
        userModel.setPassword(login_binding.userpassword.getText().toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Serveur.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AmphinoteApi amphinoteApi = retrofit.create(AmphinoteApi.class);

        Call<UserModel> call = amphinoteApi.getUserdata(userModel.getAuth(),userModel.getEmail());
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this,"Mauvaise Combinaison",Toast.LENGTH_SHORT).show();
                    return;
                }
                UserModel User = response.body();

                SharedPreferences  mPrefs = getApplicationContext().getSharedPreferences("UserInfo",MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(User);
                prefsEditor.putString("UserModel", json);
                prefsEditor.apply();

                Toast.makeText(LoginActivity.this,"Bonjour "+ json ,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, UserAreaActivity.class));
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Serveur inaccessible"+t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }

}

