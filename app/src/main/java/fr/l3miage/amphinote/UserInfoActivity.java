package fr.l3miage.amphinote;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fr.l3miage.amphinote.databinding.ActivityUserInfoBinding;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import fr.l3miage.amphinote.model.UserModel;
import fr.l3miage.amphinote.utils.Serveur;


public class UserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        ActivityUserInfoBinding activityUserInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_info);
        activityUserInfoBinding.settingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserInfoActivity.this, SettingsActivity.class));
            }
        });

        activityUserInfoBinding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        SharedPreferences mPrefs = getSharedPreferences("UserInfo",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("UserModel", "");
        UserModel userModel = gson.fromJson(json, UserModel.class);

        Picasso.get().load(Serveur.url+userModel.getPhoto()).into(activityUserInfoBinding.profileImage);
        activityUserInfoBinding.setUser(userModel);

    }
}
