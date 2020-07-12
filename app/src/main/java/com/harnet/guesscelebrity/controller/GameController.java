package com.harnet.guesscelebrity.controller;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.harnet.guesscelebrity.model.Game;

public class GameController {
    private Game game = Game.getInstance();

    private int celebrityNum;

    private CelebrityController celebrityController;
    private ImageController imageController;

    private ImageView celebrityImageView;
    private Button answer4Button;

    public GameController(ImageView celebrityImageView, Button answer4Button) {
        this.celebrityImageView = celebrityImageView;
        this.answer4Button = answer4Button;
        celebrityController = CelebrityController.getInstance();
        imageController = new ImageController();

        // initial celebrity
        celebrityImageView.setImageBitmap(imageController.getImageByLink(celebrityController.getCelebrities().get(celebrityNum).getPhotoLink()));
        answer4Button.setText(celebrityController.getCelebrities().get(celebrityNum).getName());

        onClickCreator();
    }

    public void onClickCreator(){
        answer4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                celebrityNum++;
                imageController = new ImageController();
                answer4Button.setText(celebrityController.getCelebrities().get(celebrityNum).getName());
                celebrityImageView.setImageBitmap(imageController.getImageByLink(celebrityController.getCelebrities().get(celebrityNum).getPhotoLink()));
            }
        });
    }

    public void newGame(){
        game.setGame(true);
    }
}