package com.longshihan.mymenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private MainUI mainUI;
    private LeftMenu leftMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainUI = new MainUI(this);
        setContentView(mainUI);
        leftMenu = new LeftMenu();
        getSupportFragmentManager().beginTransaction()
                .add(MainUI.LEFT_ID, leftMenu).commit();
    }

}
