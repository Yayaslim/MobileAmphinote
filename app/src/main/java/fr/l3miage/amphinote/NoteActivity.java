package fr.l3miage.amphinote;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import fr.l3miage.amphinote.databinding.ActivityNoteBinding;
import fr.l3miage.amphinote.model.NoteModel;
import fr.l3miage.amphinote.model.UserModel;
import fr.l3miage.amphinote.utils.Serveur;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoteActivity extends AppCompatActivity {
    private ActivityNoteBinding binding;
    private NoteModel noteModel;
    private SharedPreferences mPrefs;
    private UserModel userModel;
    private Integer liked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note);
        mPrefs = getSharedPreferences("UserInfo",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("UserModel", "");
        userModel = gson.fromJson(json, UserModel.class);
        noteModel = (NoteModel) getIntent().getSerializableExtra("Note");
        Picasso.get().load(noteModel.getPath()).into(binding.notePerso);
        binding.title.setText(noteModel.getTitre());
        binding.desc.setText(noteModel.getDescription());
        isLiked();
    }

    public void Like(View arg0){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Serveur.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AmphinoteApi amphinoteApi = retrofit.create(AmphinoteApi.class);
        Call<Integer> call = amphinoteApi.setLike(-(liked),userModel.getId(),noteModel.getId());
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(NoteActivity.this,"ERREUR 1",Toast.LENGTH_SHORT).show();
                    return;
                }
                liked=response.body();
                binding.textView2.setText(display(liked));
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(NoteActivity.this,"Serveur inaccessible 1 "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }

    public void isLiked(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Serveur.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AmphinoteApi amphinoteApi = retrofit.create(AmphinoteApi.class);
        Integer userid =userModel.getId();
        Integer noteid =noteModel.getId();
        Call<Integer> call = amphinoteApi.getLike(userid,noteid);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(NoteActivity.this,"ERREUR 2",Toast.LENGTH_SHORT).show();
                    return;
                }
                liked=response.body();
                binding.textView2.setText(display(liked));
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(NoteActivity.this,"Serveur inaccessible 2 "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    public String display(Integer integer){
        if(integer == -1) {
            return "Is not liked";
        }else
            {
            return "Is liked";
        }
    }


}