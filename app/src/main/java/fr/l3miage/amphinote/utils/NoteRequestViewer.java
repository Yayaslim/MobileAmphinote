package fr.l3miage.amphinote.utils;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;
import fr.l3miage.amphinote.AmphinoteApi;
import fr.l3miage.amphinote.NoteRecyclerViewDataAdapter;
import fr.l3miage.amphinote.model.NoteModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoteRequestViewer {

    private Map<String,Object> map;
    private Context context;
    private NoteRecyclerViewDataAdapter adapter;
    private RecyclerView recyclerView;

    public NoteRequestViewer(Map<String, Object> map, Context context, NoteRecyclerViewDataAdapter adapter, RecyclerView recyclerView) {
        this.map = map;
        this.context = context;
        this.adapter = adapter;
        this.recyclerView = recyclerView;
    }

    public void initializeNote() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Serveur.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AmphinoteApi amphinoteApi = retrofit.create(AmphinoteApi.class);
        Call<List<NoteModel>> call = amphinoteApi.getNote(map);
        call.enqueue(new Callback<List<NoteModel>>() {
            @Override
            public void onResponse(Call<List<NoteModel>> call, Response<List<NoteModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Aucune Note trouver", Toast.LENGTH_SHORT).show();
                    return;

                }
                generateNoteList(response.body());
            }

            @Override
            public void onFailure(Call<List<NoteModel>> call, Throwable t) {
                Toast.makeText(context, "Serveur inaccessible " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void generateNoteList(List<NoteModel> empDataList) {

        adapter = new NoteRecyclerViewDataAdapter(context,empDataList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,2);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}
