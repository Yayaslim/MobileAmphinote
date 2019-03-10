package fr.l3miage.amphinote.fragment;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fr.l3miage.amphinote.AmphinoteApi;
import fr.l3miage.amphinote.ImagePicker;
import fr.l3miage.amphinote.R;
import fr.l3miage.amphinote.databinding.FragmentAddNoteBinding;
import fr.l3miage.amphinote.model.NoteModel;
import fr.l3miage.amphinote.utils.Serveur;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class AddNoteFragment extends Fragment {
    private static final int PICK_IMAGE_ID = 234;
    FragmentAddNoteBinding binding;
    Bitmap bitmap;

    public AddNoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false);
        onPickImage(container);
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendNote();
            }
        });
        return binding.getRoot();
    }
    public void onPickImage(View view) {
        Intent chooseImageIntent = ImagePicker.getPickImageIntent(getContext());
        startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case PICK_IMAGE_ID:
                bitmap = ImagePicker.getImageFromResult(getContext(), resultCode, data);
                if(bitmap==null){
                    binding.note.setImageResource(R.mipmap.logo_amphinote);
                }else{
                binding.note.setImageBitmap(bitmap);
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }

    }


    public void SendNote(){
        if (bitmap==null){
            Toast.makeText(getContext(),"Veuillez choisir une Image",Toast.LENGTH_LONG).show();
            return;
        }

        File file = new File(Environment.getExternalStorageDirectory()
                + File.separator + binding.editTitle.getText().toString().replaceAll("[^a-zA-Z0-9 ]", "")
                +getArguments().getInt("userid")
                +".png");

        Bitmap bit = bitmap;

try {
            file.createNewFile();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bit.compress(Bitmap.CompressFormat.PNG, 100, bos);
            byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        }
catch (IOException o){
    Toast.makeText(getContext(), "Opération Impossible blblblbl",Toast.LENGTH_SHORT).show();
}
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Serveur.url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RequestBody desc_part = RequestBody.create(MultipartBody.FORM,binding.editdesc.getText().toString());
        RequestBody title_part = RequestBody.create(MultipartBody.FORM,binding.editTitle.getText().toString());
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageupload", file.getName(), reqFile);

        AmphinoteApi amphinoteApi = retrofit.create(AmphinoteApi.class);
        Call<NoteModel> call = amphinoteApi.setNote(title_part,
                                                    desc_part,
                                                    body,getArguments().getInt("userid"));


        call.enqueue(new Callback<NoteModel>() {
            @Override
            public void onResponse(Call<NoteModel> call, Response<NoteModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(),"ERREUR",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getContext(),"Reussi",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<NoteModel> call, Throwable t) {
                Toast.makeText(getContext(),"Serveur inaccessible "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



    }

}
