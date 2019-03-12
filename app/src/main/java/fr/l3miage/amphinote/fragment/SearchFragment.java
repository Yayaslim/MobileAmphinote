package fr.l3miage.amphinote.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import fr.l3miage.amphinote.NoteRecyclerViewDataAdapter;
import fr.l3miage.amphinote.R;
import fr.l3miage.amphinote.databinding.FragmentHomeBinding;
import fr.l3miage.amphinote.utils.NoteRequestViewer;

public class SearchFragment extends Fragment {
    private FragmentHomeBinding binding;
    private NoteRecyclerViewDataAdapter adapter;

    public SearchFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("search", getArguments().getString("Query"));

        NoteRequestViewer requestViewer = new NoteRequestViewer(map,getContext(),adapter,binding.cardViewRecyclerList);
        requestViewer.initializeNote();
        return binding.getRoot();
    }
}
