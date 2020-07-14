package com.harnet.guesscelebrity.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.harnet.guesscelebrity.R;
import com.harnet.guesscelebrity.controller.CelebrityController;
import com.harnet.guesscelebrity.controller.GameController;
import com.harnet.guesscelebrity.model.Celebrity;
import com.harnet.guesscelebrity.model.Game;

public class GameFragment extends Fragment {
    private GameController gameController;
    private GameFragment.OnMessageSendListener onMessageSendListener;

    private ImageView celebrityImageView;
    private TextView celebrityNumTextView;
    private TextView wrongAnswersQttTextView;
    private LinearLayout answersBlockLinearLayout;
    private Button answer4Button; // TODO delete it
    private Button trainingButton;
    private FrameLayout gameContentFrameLayout;


    // interface for exchanging data between fragments
    public interface OnMessageSendListener{
        public void onMessageSend(String message);
    }

    public GameFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment!!! Do the findViewById available
        View view =  inflater.inflate(R.layout.fragment_game, container, false);

        celebrityImageView = view.findViewById(R.id.celebrity_imageView);
        celebrityNumTextView = view.findViewById(R.id.celebrity_num_textView);
        answersBlockLinearLayout = view.findViewById(R.id.answers_block_LinearLayout);
        answer4Button = view.findViewById(R.id.answer4_button);
        trainingButton = view.findViewById(R.id.training_button);
        wrongAnswersQttTextView = view.findViewById(R.id.wrong_answers_textView);
        gameContentFrameLayout = view.findViewById(R.id.game_content_FrameLayout);

        gameController = GameController.getInstance(getContext(), answersBlockLinearLayout, celebrityNumTextView, wrongAnswersQttTextView,
                celebrityImageView,answer4Button);

        // Inflate the layout for this fragment!!! Do the findViewById available
        trainingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Training";// message for sending
                onMessageSendListener.onMessageSend(message); // send message to main activity
            }
        });

        //for testing print all celebrities with photo links
//        CelebrityController celebrityController = CelebrityController.getInstance();
//        assert celebrityController != null;
//        for(Celebrity celebrity : celebrityController.getCelebrities()){
//            System.out.println(celebrity.getName() + " : " +celebrity.getPhotoLink() );
//        }
//        Log.i("Link:", "onCreate: " + celebrityController.getCelebrities().get(0).getPhotoLink());

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        try {
            onMessageSendListener = (GameFragment.OnMessageSendListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+ "must implemented onMessageSend");
        }
    }
}