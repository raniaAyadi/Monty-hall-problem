package com.example.rayadi001.monty_hall;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener{
    int carDoor, revealedGoatDoor, selectedDoor;
    String inUrl = "https://fr.wikipedia.org/wiki/Probl%C3%A8me_de_Monty_Hall";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView imageViewDoor1 = (ImageView) findViewById(R.id.door1);
        ImageView imageViewDoor2 = (ImageView) findViewById(R.id.door2);
        ImageView imageViewDoor3 = (ImageView) findViewById(R.id.door3);

        imageViewDoor1.setOnClickListener(myhandler1);
        imageViewDoor2.setOnClickListener(myhandler2);
        imageViewDoor3.setOnClickListener(myhandler3);
    }

    View.OnClickListener myhandler1 = new View.OnClickListener() {
        public void onClick(View view) {
            // it was the 1st button
            Toast toast = Toast.makeText(getApplicationContext(), "door1", Toast.LENGTH_SHORT);
            toast.show();


            Intent intent = new Intent(view.getContext(), Main2Activity.class);
            intent.putExtra("door", "1");
            startActivity(intent);
        }
    };
    View.OnClickListener myhandler2 = new View.OnClickListener() {
        public void onClick(View view) {
            // it was the 2nd button
            Toast toast = Toast.makeText(getApplicationContext(), "door2", Toast.LENGTH_SHORT);
            toast.show();


            Intent intent = new Intent(view.getContext(), Main2Activity.class);
            intent.putExtra("door", "2");
            startActivity(intent);
        }
    };

    View.OnClickListener myhandler3= new View.OnClickListener() {
        public void onClick(View view) {
            // it was the 2nd button
            Toast toast = Toast.makeText(getApplicationContext(), "door3", Toast.LENGTH_SHORT);
            toast.show();


            Intent intent = new Intent(view.getContext(), Main2Activity.class);
            intent.putExtra("door", "3");
            startActivity(intent);
        }
    };

    public void onHelpButtonPress(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fr.wikipedia.org/wiki/Probl%C3%A8me_de_Monty_Hall"));
        startActivity(intent);
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
