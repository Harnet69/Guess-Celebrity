package com.harnet.knowyourstaff.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import com.harnet.knowyourstaff.R;
import com.harnet.knowyourstaff.controller.PersonController;

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
            startGameFragment();
        }

        //TODO find what for this thing
        if(exchangeBundle != null){
            fragment.setArguments(exchangeBundle); // record data for exchanging to arguments
        }
    }

    // receive message from Fragments ans switch Game and Training mode, and put message to exchange bundle
    @Override
    public void onMessageSend(String message) {
        exchangeBundle.putString("message", message);
        switch (message){
            case "Game" : startGameFragment();
                        break;
            case "Training" : startTrainingFragment();
                        break;
        }
    }

    private void startGameFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_FrameLayout, new GameFragment())
                .commit();
    }

    private void startTrainingFragment(){
        if(PersonController.getInstance().getStaffListByGuess(false).size() > 0) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_FrameLayout, new TrainingFragment())
                    .commit();
        }else{
            System.out.println("Isn't exists not guessed persons");
            startGameFragment();
        }
    }
}