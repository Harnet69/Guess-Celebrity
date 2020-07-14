package com.harnet.guesscelebrity.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.harnet.guesscelebrity.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class TrainingFragment extends Fragment {
    private EditText messageEditText;
    private Button sendButton;
    OnMessageSendListener onMessageSendListener;


    // interface for exchanging data between fragments
    public interface OnMessageSendListener{

        public void onMessageSend(String message);
    }

    public TrainingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment!!! Do the findViewById available
        View view =  inflater.inflate(R.layout.fragment_training, container, false);
//        messageEditText = view.findViewById(R.id.messageEditText);
//        sendButton = view.findViewById(R.id.sendButton);

//        sendButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String message = messageEditText.getText().toString();// message for sending
//                onMessageSendListener.onMessageSend(message); // send message to main activity
//            }
//        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//        Activity activity = (Activity) context;
//        try {
//            onMessageSendListener = (OnMessageSendListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()+ "must implemented onMessageSend");
//        }
    }
}