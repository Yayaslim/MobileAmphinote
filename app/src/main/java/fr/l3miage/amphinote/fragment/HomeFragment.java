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
import fr.l3miage.amphinote.utils.NoteRequestViewer;
import fr.l3miage.amphinote.utils.Serveur;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NoteRecyclerViewDataAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", getArguments().getInt("userid"));

        NoteRequestViewer requestViewer = new NoteRequestViewer(map,getContext(),adapter,binding.cardViewRecyclerList);
        requestViewer.initializeNote();
        return binding.getRoot();
    }


}

