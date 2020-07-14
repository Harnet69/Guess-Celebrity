package com.harnet.guesscelebrity.view;

import android.content.Context;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.harnet.guesscelebrity.R;
import com.harnet.guesscelebrity.controller.CelebrityController;
import com.harnet.guesscelebrity.controller.ImageController;
import com.harnet.guesscelebrity.model.Celebrity;

import java.util.ArrayList;
import java.util.List;

public class TrainingFragment extends Fragment {
    private ImageController imageController;

    private ImageView celebrityImageView;
    private TextView celebrityNameTextView;
    private EditText messageEditText;
    private Button sendButton;
    private OnMessageSendListener onMessageSendListener;

    private List<Celebrity> unGuessedCelebrities;


    // interface for exchanging data between fragments
    public interface OnMessageSendListener{

        public void onMessageSend(String message);
    }

    public TrainingFragment() {
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        imageController = new ImageController();
        unGuessedCelebrities = CelebrityController.getInstance().getListOfCelebritiesByGuess(false);

        View view =  inflater.inflate(R.layout.fragment_training, container, false);
        celebrityImageView = view.findViewById(R.id.celebrity_imageView);
        celebrityNameTextView = view.findViewById(R.id.celebrity_name_textView);

        //TODO test the first unguessed celebrity
        celebrityImageView.setImageBitmap(imageController.getImageByLink(CelebrityController.getInstance().getCelebrityByName(unGuessedCelebrities.get(0).getName()).getPhotoLink()));
        celebrityNameTextView.setText(unGuessedCelebrities.get(0).getName());

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}