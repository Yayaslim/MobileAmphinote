package fr.l3miage.amphinote;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import fr.l3miage.amphinote.model.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.201/amphinote/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AmphinoteApi amphinoteApi = retrofit.create(AmphinoteApi.class);

        Call<List<UserModel>> call = amphinoteApi.getUserdata();

        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<UserModel> posts = response.body();

                for (UserModel post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "Prenom: " + post.getUsername() + "\n";
                    content += "Nom: " + post.getLastname() + "\n";
                    content += "mail: " + post.getMail() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });*/
    }
}