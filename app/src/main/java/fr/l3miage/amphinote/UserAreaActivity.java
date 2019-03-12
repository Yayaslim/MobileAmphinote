package fr.l3miage.amphinote;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
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
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        showFragment(homeFragment);

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

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Etes vous sur de vouloir quitter?")
                .setCancelable(false)
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        UserAreaActivity.this.finish();
                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.background));
        alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.background));

    }

}
