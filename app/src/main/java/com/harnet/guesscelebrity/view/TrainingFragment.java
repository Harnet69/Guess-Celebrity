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
import com.harnet.guesscelebrity.controller.GameController;
import com.harnet.guesscelebrity.controller.ImageController;
import com.harnet.guesscelebrity.controller.TrainingController;
import com.harnet.guesscelebrity.model.Celebrity;

import java.util.ArrayList;
import java.util.List;

public class TrainingFragment extends Fragment {
    private TrainingController trainingController;

    // interface for exchanging data between fragments
    public interface OnMessageSendListener{

        public void onMessageSend(String message);
    }

    public TrainingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_training, container, false);

        trainingController = new TrainingController(view);

        trainingController.trainingLoop();

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}