package fr.l3miage.amphinote;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

import fr.l3miage.amphinote.model.NoteModel;

public class NoteRecyclerViewDataAdapter extends RecyclerView.Adapter<NoteRecyclerViewDataAdapter.ViewHolder> {
    private List<NoteModel> noteModels;
    private Context context;

    public NoteRecyclerViewDataAdapter(Context context, List noteModels) {
        this.context = context;
        this.noteModels = noteModels;

    }

    @Override
    public NoteRecyclerViewDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_note, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

        viewHolder.tv_model.setText(noteModels.get(i).getDenomination());
        viewHolder.like.setText(String.valueOf(noteModels.get(i).getAime()));
        Picasso.get().load(noteModels.get(i).getPath()).into(viewHolder.img_model);
        viewHolder.img_model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(view.getContext(), NoteActivity.class);
                intent.putExtra("Note",noteModels.get(i));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return noteModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_model;
        ImageView img_model;
        TextView like;
        public ViewHolder(View view) {
            super(view);

            tv_model = (TextView)view.findViewById(R.id.card_view_image_title);
            img_model = (ImageView)view.findViewById(R.id.card_view_image);
            like = (TextView)view.findViewById(R.id.indicator);

        }
    }

}