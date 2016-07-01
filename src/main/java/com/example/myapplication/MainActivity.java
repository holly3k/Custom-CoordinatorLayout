package com.example.myapplication;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


//        TranslateAnimation animation =new TranslateAnimation(Animation.RELATIVE_TO_SELF , 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF , 0, Animation.RELATIVE_TO_SELF , 3f);
//        animation.setDuration(3000);
//        animation.setInterpolator( new AccelerateInterpolator());
//        iv.startAnimation(animation);
        /*final View iv = findViewById(R.id.first);
        Runnable r = new Runnable(){
            @Override
            public void run() {

                int i = 0;
                while(i < 10) {
                    iv.setY(iv.getY()+50);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        };
        new Thread(r).start();*/

    }

}
