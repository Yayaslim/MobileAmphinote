package fr.l3miage.amphinote;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import fr.l3miage.amphinote.databinding.ActivityUserAreaBinding;

public class UserAreaActivity extends AppCompatActivity {

    ActivityUserAreaBinding userAreaBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);


    }
}
