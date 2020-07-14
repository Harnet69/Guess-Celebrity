package com.harnet.guesscelebrity.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import com.harnet.guesscelebrity.R;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private Bundle exchangeBundle; // bundle to keep data for exchanging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new Fragment();

        // fragments migration
        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.game_content_FrameLayout, new GameFragment())
                    .commit();
        }
    }
}