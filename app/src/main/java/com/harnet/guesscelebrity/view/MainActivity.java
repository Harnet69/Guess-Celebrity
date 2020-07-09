package com.harnet.guesscelebrity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.harnet.guesscelebrity.R;
import com.harnet.guesscelebrity.controller.SiteParserController;

public class MainActivity extends AppCompatActivity {
    private SiteParserController siteParserController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        siteParserController = new SiteParserController();
        siteParserController.parse();
        System.out.println(siteParserController.getSourceSite().getContext());
    }
}