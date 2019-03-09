package fr.l3miage.amphinote;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import fr.l3miage.amphinote.databinding.ActivityUserAreaBinding;
import fr.l3miage.amphinote.databinding.ActivityUserInfoBinding;
import fr.l3miage.amphinote.model.UserModel;

public class UserInfoActivity extends AppCompatActivity {

    ActivityUserInfoBinding activityUserInfoBinding;

    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        activityUserInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_info);


        SharedPreferences mPrefs = getSharedPreferences("UserInfo",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("UserModel", "");
        userModel = gson.fromJson(json, UserModel.class);
        Picasso.get().load("http://192.168.2.201/amphinote/"+userModel.getPhoto()).into(activityUserInfoBinding.profileImage);
        Toast.makeText(UserInfoActivity.this,"Bonjour "+userModel.getUsername()+" : "+userModel.getLastname(),Toast.LENGTH_LONG).show();
        activityUserInfoBinding.setUser(userModel);
    }
}
