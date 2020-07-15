package com.harnet.guesscelebrity.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import com.harnet.guesscelebrity.R;

public class MainActivity extends AppCompatActivity implements GameFragment.OnMessageSendListener, TrainingFragment.OnMessageSendListener{
    private Fragment fragment;
    private Bundle exchangeBundle; // bundle to keep data for exchanging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new Fragment();
        exchangeBundle = new Bundle();

        // default fragment
        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.game_content_FrameLayout, new GameFragment())
                    .commit();
        }

        // if game ask to start training //TODO find what for this thing
        if(exchangeBundle != null){
            fragment.setArguments(exchangeBundle); // record data for exchanging to arguments
        }
    }

    // receive message from Fragments ans switch Game and Training mode, and put message to exchange bundle
    @Override
    public void onMessageSend(String message) {
        exchangeBundle.putString("message", message);

        if(message.equals("Game")){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.game_content_FrameLayout, new GameFragment())
                    .commit();
        }else if(message.equals("Training")){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.game_content_FrameLayout, new TrainingFragment())
                    .commit();
        }
    }
}