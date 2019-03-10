package fr.l3miage.amphinote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import fr.l3miage.amphinote.databinding.ActivityUserAreaBinding;
import fr.l3miage.amphinote.fragment.AddNoteFragment;
import fr.l3miage.amphinote.fragment.HomeFragment;
import fr.l3miage.amphinote.fragment.YourNoteFragment;
import fr.l3miage.amphinote.model.UserModel;
public class UserAreaActivity extends AppCompatActivity {

    ActivityUserAreaBinding userAreaBinding;

    Button showSettings;
    TextView txtInfo;
    UserModel userModel;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        SharedPreferences mPrefs = getSharedPreferences("UserInfo",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("UserModel", "");
        userModel = gson.fromJson(json, UserModel.class);

        bundle = new Bundle();
        bundle.putInt("userid", userModel.getId());

        userAreaBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_area);
        userAreaBinding.navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        userAreaBinding.profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserAreaActivity.this, UserInfoActivity.class));
            }
        });
        /*
        showSettings = (Button) findViewById(R.id.showSettings);
        txtInfo = (TextView) findViewById(R.id.txtInfo);


        showSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                StringBuilder info = new StringBuilder();

                info.append("Name : "+sharedPreferences.getString("key_full_name", " -1"));
                info.append("\nEmail : "+sharedPreferences.getString("key_email", " -1"));
                info.append("\nEnable sleep timer : "+sharedPreferences.getBoolean("enable_timer", false));
                info.append("\nSleep timer : "+sharedPreferences.getString("key_sleep_timer", "-1"));
                info.append("\nMusic Quality : "+sharedPreferences.getString("key_music_quality", "-1"));
                info.append("\nTypes Of Music : "+sharedPreferences.getStringSet("music_type", null));

                txtInfo.setText(info);
            }
        });*/
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment homeFragment = new HomeFragment();
                    homeFragment.setArguments(bundle);
                    showFragment(homeFragment);
                    return true;
                case R.id.add_note:
                    AddNoteFragment addNoteFragment = new AddNoteFragment();
                    addNoteFragment.setArguments(bundle);
                    showFragment(addNoteFragment);

                    return true;
                case R.id.your_note:
                    YourNoteFragment yourNoteFragment = new YourNoteFragment();
                    yourNoteFragment.setArguments(bundle);
                    showFragment(yourNoteFragment);
                    return true;
            }
            return false;
        }

    };

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }


}
