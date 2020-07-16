package com.harnet.knowyourstaff.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harnet.knowyourstaff.R;
import com.harnet.knowyourstaff.controller.TrainingController;

public class TrainingFragment extends Fragment {
    private OnMessageSendListener onMessageSendListener;

    // interface for exchanging data between fragments
    public interface OnMessageSendListener{
        void onMessageSend(String message);
    }

    public TrainingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_training, container, false);
        new TrainingController(view, onMessageSendListener).trainingLoop();

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        try {
            onMessageSendListener = (OnMessageSendListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+ "must implemented onMessageSend");
        }
    }
}