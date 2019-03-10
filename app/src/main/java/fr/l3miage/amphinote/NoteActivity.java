package fr.l3miage.amphinote;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.squareup.picasso.Picasso;
import fr.l3miage.amphinote.databinding.ActivityNoteBinding;
import fr.l3miage.amphinote.model.NoteModel;

public class NoteActivity extends AppCompatActivity {
    private ActivityNoteBinding binding;

    private static final String TAG = NoteActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note);
        NoteModel noteModel = (NoteModel) getIntent().getSerializableExtra("Note");
        Picasso.get().load(noteModel.getPath()).into(binding.notePerso);
        binding.title.setText(noteModel.getTitre());
        binding.desc.setText(noteModel.getDescription());



    }

}