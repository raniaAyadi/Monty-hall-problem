package com.example.rayadi001.monty_hall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.StringTokenizer;

import static android.widget.Toast.*;

public class Main2Activity extends AppCompatActivity {
    int carDoor, revealedGoatDoor,selectedDoor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        generateNewSimulation();

        String door = bundle.getString("door");
        switch (door) {
            case "1": {
                ImageView imageView1 = (ImageView) findViewById(R.id.door1);
                imageView1.setImageResource(R.drawable.selected_closed_door);
                selectedDoor=1;
                break;
            }
            case "2": {
                ImageView imageView2 = (ImageView) findViewById(R.id.door2);
                imageView2.setImageResource(R.drawable.selected_closed_door);
                selectedDoor=2;
                break;
            }
            case "3": {
                ImageView imageView3 = (ImageView) findViewById(R.id.door3);
                imageView3.setImageResource(R.drawable.selected_closed_door);
                selectedDoor=3;
                break;
            }
        }
        revealGoat();
    }

    public ImageView getDoorImageView(int door) {
        switch (door) {
            case 1:
                return (ImageView) findViewById(R.id.door1);
            case 2:
                return (ImageView) findViewById(R.id.door2);
            case 3:
                return (ImageView) findViewById(R.id.door3);
            default:
                Log.e("MainActivity", "getDoorImageView: No image view found");
                return null;
        }
    }
    public void revealGoatDoor() {
        revealedGoatDoor = 0;
        // get goat door
        if (selectedDoor == carDoor) {
            Random r = new Random();

            do {
                revealedGoatDoor = r.nextInt(3) + 1;
            } while (revealedGoatDoor == selectedDoor);
        } else
            revealedGoatDoor = 6 - selectedDoor - carDoor;

        Log.d("Logic", "Goat revealed on door no." + revealedGoatDoor);
    }

    public void generateNewSimulation() {
        Log.d("Logic", "Generating new simulation");

        // randomly place the car
        Random r = new Random();
        carDoor = r.nextInt(3) + 1;

        Log.d("Logic", "The car is on door no. " + carDoor);
    }

    public void revealGoat() {
        revealGoatDoor();

        ImageView imageView = getDoorImageView(revealedGoatDoor);
        imageView.setImageResource(R.drawable.goat_open_door);
    }
    public void swapSelectedDoor() {
        selectedDoor = 6 - selectedDoor - revealedGoatDoor;
    }
    public int getRemainingDoor() {
        return 6 - selectedDoor - revealedGoatDoor;
    }

    public void showProblemResult(boolean gotTheCar) {
        // hide step 2 layout
        findViewById(R.id.step2).setVisibility(View.GONE);

        ImageView imageView = getDoorImageView(selectedDoor);
        ImageView remainingDoorImageView = getDoorImageView(getRemainingDoor());
        TextView label_result = (TextView) findViewById(R.id.label_result);

        if (gotTheCar) {
            imageView.setImageResource(R.drawable.selected_car_open_door);
            remainingDoorImageView.setImageResource(R.drawable.goat_open_door);
            label_result.setText(getString(R.string.label_car_result));
        } else {
            imageView.setImageResource(R.drawable.selected_goat_open_door);
            remainingDoorImageView.setImageResource(R.drawable.car_open_door);
            label_result.setText(getString(R.string.label_goat_result));
        }

        label_result.setVisibility(View.VISIBLE);
    }


    public void onKeepPress(View view) {
        Log.d("MainActivity", "Pressed keep button");

        Button button_swap = (Button) findViewById(R.id.button_swap);
        Button button_keep = (Button) findViewById(R.id.button_keep);

        button_keep.setEnabled(false);
        button_swap.setEnabled(false);


        showProblemResult(selectedDoor == carDoor);
    }
    public void onSwapPress(View view){
        Log.d("MainActivity", "Pressed swap button");

        //disableSwapAndKeepButtons();

        ImageView imageView = getDoorImageView(revealedGoatDoor);
        imageView.setImageResource(R.drawable.closed_door);
        imageView.setEnabled(false);
        //swapSelectedDoor();
        //showProblemResult(selectedDoor == carDoor);

        Button button_swap = (Button) findViewById(R.id.button_swap);
        Button button_keep = (Button) findViewById(R.id.button_keep);

        button_keep.setEnabled(false);
        button_swap.setEnabled(false);
    }

    public void door1Selected(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.door1);
        imageView.setImageResource(R.drawable.selected_closed_door);
        selectedDoor = 1;
        showProblemResult(selectedDoor == carDoor);
    }

    public void door2Selected(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.door2);
        imageView.setImageResource(R.drawable.selected_closed_door);
        selectedDoor=2;
        showProblemResult(selectedDoor == carDoor);
    }

    public void door3Selected(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.door3);
        imageView.setImageResource(R.drawable.selected_closed_door);
        selectedDoor=3;
        showProblemResult(selectedDoor == carDoor);
    }
    public void closeAllDoors() {
        ImageView imageView;

        imageView = (ImageView) findViewById(R.id.door1);
        imageView.setImageResource(R.drawable.closed_door);

        imageView = (ImageView) findViewById(R.id.door2);
        imageView.setImageResource(R.drawable.closed_door);

        imageView = (ImageView) findViewById(R.id.door3);
        imageView.setImageResource(R.drawable.closed_door);
    }

    public void onRestartButtonPress(View view) {
        Log.d("MainActivity", "Pressed restart button");

        // generate a new monty hall problem simulation
        generateNewSimulation();

        closeAllDoors();
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);

        // show step 2 layout
        findViewById(R.id.step2).setVisibility(View.VISIBLE);

        // disable restart button
        findViewById(R.id.button_restart).setEnabled(false);

        // hide result label
        findViewById(R.id.label_result).setVisibility(View.INVISIBLE);
    }

}