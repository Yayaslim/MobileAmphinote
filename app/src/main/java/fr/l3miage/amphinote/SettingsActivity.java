package fr.l3miage.amphinote;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.l3miage.amphinote.databinding.ActivitySettingsBinding;
import fr.l3miage.amphinote.model.UserModel;
import fr.l3miage.amphinote.utils.Serveur;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding binding;
    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);

        SharedPreferences mPrefs = getSharedPreferences("UserInfo",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("UserModel", "");
        userModel = gson.fromJson(json, UserModel.class);
        binding.setSetting(userModel);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void ChangeEmail(View arg0){
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Serveur.url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        String Query ="{\"email\" : \""+binding.changeMail.getText().toString()+"\"}";
        Toast.makeText(SettingsActivity.this,Query,Toast.LENGTH_LONG).show();
        AmphinoteApi amphinoteApi = retrofit.create(AmphinoteApi.class);
        Call<UserModel> call = amphinoteApi.changeUser(userModel.getId(),Query);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(SettingsActivity.this, "ERREUR", Toast.LENGTH_SHORT).show();
                    return;

                }
                UserModel User = response.body();

                SharedPreferences  mPrefs = getApplicationContext().getSharedPreferences("UserInfo",MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(User);
                prefsEditor.putString("UserModel", json);
                prefsEditor.apply();

                Toast.makeText(SettingsActivity.this,"Bonjour "+ json ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(SettingsActivity.this,"Serveur inaccessible "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    public void ChangePass(View arg0){
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Serveur.url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        String Query ="{\"password\" : \""+binding.changePass.getText().toString()+"\"}";
        Toast.makeText(SettingsActivity.this,Query,Toast.LENGTH_LONG).show();
        AmphinoteApi amphinoteApi = retrofit.create(AmphinoteApi.class);
        Call<UserModel> call = amphinoteApi.changeUser(userModel.getId(),Query);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(SettingsActivity.this, "ERREUR", Toast.LENGTH_SHORT).show();
                    return;

                }
                UserModel User = response.body();

                SharedPreferences  mPrefs = getApplicationContext().getSharedPreferences("UserInfo",MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(User);
                prefsEditor.putString("UserModel", json);
                prefsEditor.apply();

                Toast.makeText(SettingsActivity.this,"Bonjour "+ json ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(SettingsActivity.this,"Serveur inaccessible "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}


