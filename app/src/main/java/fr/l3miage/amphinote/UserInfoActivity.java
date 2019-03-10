package fr.l3miage.amphinote;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fr.l3miage.amphinote.databinding.ActivityUserInfoBinding;

public class UserInfoActivity extends AppCompatActivity {
    ActivityUserInfoBinding activityUserInfoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        activityUserInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_info);
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
    }
}
