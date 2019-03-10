package fr.l3miage.amphinote;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.l3miage.amphinote.databinding.RegisterActivityBinding;
import fr.l3miage.amphinote.model.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegisterActivity extends AppCompatActivity {
    RegisterActivityBinding registerActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        registerActivityBinding = DataBindingUtil.setContentView(this, R.layout.register_activity);

        registerActivityBinding.createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Signin();
            }
        });
    }

    protected void Signin(){
        String age =  registerActivityBinding.age.getText().toString();
        String usermail = registerActivityBinding.mail.getText().toString();
        String lastname = registerActivityBinding.nom.getText().toString();
        String password = registerActivityBinding.pwd.getText().toString();
        String username = registerActivityBinding.prenom.getText().toString();

        if(age.equals("") || usermail.equals("") || lastname.equals("")|| password.equals("")|| username.equals("")){

            Toast.makeText(RegisterActivity.this,"Veuillez entrer tout les champs",Toast.LENGTH_SHORT).show();
            return;
        }

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.14/amphinote/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        AmphinoteApi amphinoteApi = retrofit.create(AmphinoteApi.class);

        Call<UserModel> call = amphinoteApi.setUser(usermail,lastname,username,password,age);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"L'email est deja utilisé",Toast.LENGTH_SHORT).show();
                    return;
                }
                    UserModel UserResponse = response.body();
                    Toast.makeText(RegisterActivity.this,"Compte cree avec succes",Toast.LENGTH_SHORT).show();
                    Toast.makeText(RegisterActivity.this,"Veuillez Vous Connectez "+UserResponse.getUsername(),Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));


            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,"Serveur inaccessible "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
