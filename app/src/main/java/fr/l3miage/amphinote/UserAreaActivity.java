package fr.l3miage.amphinote;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.View;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.gson.Gson;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import fr.l3miage.amphinote.databinding.ActivityUserAreaBinding;
import fr.l3miage.amphinote.fragment.AddNoteFragment;
import fr.l3miage.amphinote.fragment.HomeFragment;
import fr.l3miage.amphinote.fragment.YourNoteFragment;
import fr.l3miage.amphinote.model.UserModel;
public class UserAreaActivity extends AppCompatActivity {

    private ActivityUserAreaBinding userAreaBinding;
    private Bundle bundle;
    private Integer sort = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        SharedPreferences mPrefs = getSharedPreferences("UserInfo",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("UserModel", "");
        UserModel userModel = gson.fromJson(json, UserModel.class);
        bundle = new Bundle();
        bundle.putString("Query","");
        bundle.putString("Filter","Rand()");
        bundle.putString("Order","DESC");
        userAreaBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_area);
        userAreaBinding.navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        userAreaBinding.profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserAreaActivity.this, UserInfoActivity.class));
            }
        });
        showFragment(new HomeFragment());


        userAreaBinding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                bundle.putString("Query",s);
                if(sort==1){
                    bundle.putString("Filter","aime");
                    bundle.putString("Order","DESC");
                }else {
                    bundle.putString("Filter","Rand()");
                    bundle.putString("Order","DESC");
                }
                showFragment(new HomeFragment());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });




        KeyboardVisibilityEvent.setEventListener(
                UserAreaActivity.this,
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        if (isOpen){
                            userAreaBinding.navigationView.setVisibility(View.INVISIBLE);
                        }else{
                            userAreaBinding.navigationView.setVisibility(View.VISIBLE);
                        }
                    }
                });
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
        fragment.setArguments(bundle);
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

    public void Spinner(View arg0){
        PopupMenu popup = new PopupMenu(UserAreaActivity.this,userAreaBinding.imageView3 );
        popup.getMenuInflater().inflate(R.menu.spinner, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast.makeText(UserAreaActivity.this,String.valueOf(menuItem.getOrder()),Toast.LENGTH_LONG).show();
                sort=menuItem.getOrder();
                return true;
            }
        });
        popup.show();

    }
}
