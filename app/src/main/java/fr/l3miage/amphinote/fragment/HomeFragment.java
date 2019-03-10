package fr.l3miage.amphinote.fragment;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.l3miage.amphinote.AmphinoteApi;
import fr.l3miage.amphinote.LoginActivity;
import fr.l3miage.amphinote.NoteRecyclerViewDataAdapter;
import fr.l3miage.amphinote.R;
import fr.l3miage.amphinote.RegisterActivity;
import fr.l3miage.amphinote.databinding.FragmentHomeBinding;
import fr.l3miage.amphinote.model.NoteModel;
import fr.l3miage.amphinote.utils.Serveur;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    private NoteRecyclerViewDataAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        initializeNote();
        // Set data adapter.
        return binding.getRoot();
    }

    private void initializeNote() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Serveur.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AmphinoteApi amphinoteApi = retrofit.create(AmphinoteApi.class);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", getArguments().getInt("userid"));

        Call<List<NoteModel>> call = amphinoteApi.getNote(map);

        call.enqueue(new Callback<List<NoteModel>>() {
            @Override
            public void onResponse(Call<List<NoteModel>> call, Response<List<NoteModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "ERREUR", Toast.LENGTH_SHORT).show();
                    return;

                }
                generateNoteList(response.body());
            }

            @Override
            public void onFailure(Call<List<NoteModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Serveur inaccessible " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void generateNoteList(List<NoteModel> empDataList) {

        adapter = new NoteRecyclerViewDataAdapter(getContext(),empDataList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);

        binding.cardViewRecyclerList.setLayoutManager(layoutManager);

        binding.cardViewRecyclerList.setAdapter(adapter);
    }

}

