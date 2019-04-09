package fr.l3miage.amphinote.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import fr.l3miage.amphinote.NoteRecyclerViewDataAdapter;
import fr.l3miage.amphinote.R;
import fr.l3miage.amphinote.databinding.FragmentAddNoteBinding;
import fr.l3miage.amphinote.databinding.FragmentYourNoteBinding;
import fr.l3miage.amphinote.utils.NoteRequestViewer;

public class YourNoteFragment extends Fragment {

    private FragmentYourNoteBinding binding;
    private NoteRecyclerViewDataAdapter noteRecyclerViewDataAdapter;

    public YourNoteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_your_note, container, false);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", getArguments().getInt("userid"));

        NoteRequestViewer requestViewer = new NoteRequestViewer(map,getContext(),noteRecyclerViewDataAdapter,binding.cardViewRecyclerList);
        requestViewer.initializeNote();
        return binding.getRoot();
    }
}
