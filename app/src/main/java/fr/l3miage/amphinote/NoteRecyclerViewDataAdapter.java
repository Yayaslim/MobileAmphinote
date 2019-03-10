package fr.l3miage.amphinote;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import fr.l3miage.amphinote.model.NoteModel;

public class NoteRecyclerViewDataAdapter extends RecyclerView.Adapter<NoteRecyclerViewDataAdapter.ViewHolder> {
    private List<NoteModel> noteModels;
    private Context context;

    public NoteRecyclerViewDataAdapter(Context context, List android_versions) {
        this.context = context;
        this.noteModels = android_versions;

    }

    @Override
    public NoteRecyclerViewDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_note, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tv_android.setText(noteModels.get(i).getTitre());
        Picasso.get().load(noteModels.get(i).getPath()).into(viewHolder.img_android);

    }

    @Override
    public int getItemCount() {
        return noteModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_android;
        ImageView img_android;
        public ViewHolder(View view) {
            super(view);

            tv_android = (TextView)view.findViewById(R.id.card_view_image_title);
            img_android = (ImageView)view.findViewById(R.id.card_view_image);
        }
    }

}