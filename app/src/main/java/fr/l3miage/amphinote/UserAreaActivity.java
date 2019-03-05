package fr.l3miage.amphinote;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.widget.TextView;

import fr.l3miage.amphinote.databinding.ActivityUserAreaBinding;
import fr.l3miage.amphinote.fragment.AddNoteFragment;
import fr.l3miage.amphinote.fragment.HomeFragment;
import fr.l3miage.amphinote.fragment.YourNoteFragment;

public class UserAreaActivity extends AppCompatActivity {

    ActivityUserAreaBinding userAreaBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        userAreaBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_area);
        userAreaBinding.navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showFragment(new HomeFragment());
                    return true;
                case R.id.add_note:
                    showFragment(new AddNoteFragment());

                    return true;
                case R.id.your_note:
                    showFragment(new YourNoteFragment());
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
