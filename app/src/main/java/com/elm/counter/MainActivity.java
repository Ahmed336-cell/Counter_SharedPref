package com.elm.counter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView Countertext;
    int counter=0;
    String CounterextSaver;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Countertext = findViewById(R.id.text);
        preferences = getSharedPreferences("saveCounterText" , MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Countertext.setText(preferences.getString("Saving" , "0"));
    }

    public void decrease(View view) {
            counter =Integer.parseInt(preferences.getString("Saving","0"));
            counter--;
            Countertext.setText(String.valueOf(counter));
            checkCounter();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Saving", String.valueOf(counter));
            editor.commit();



    }

    public void increase(View view) {
        counter =Integer.parseInt(preferences.getString("Saving","0"));
        counter++;
        Countertext.setText(String.valueOf(counter));
        checkCounter();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Saving", String.valueOf(counter));
        editor.commit();

    }

    public void reset(View view) {
        counter = 0;
        Countertext.setText(String.valueOf(counter));
        Countertext.setTextColor(Color.BLACK);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Saving", String.valueOf(counter));
        editor.commit();

    }


    public int randomColor(){
        int colors[] = {Color.BLUE,Color.RED,Color.GREEN,Color.YELLOW , Color.MAGENTA};
        int colorRandomly = new Random().nextInt(colors.length);
        return colors[colorRandomly];
    }
    public void checkCounter(){
        if (counter %10 ==0)
            Countertext.setTextColor(randomColor());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.deleteItem:
                preferences.edit().remove("Saving").apply();
                counter=0;
                Countertext.setText("0");
                Countertext.setTextColor(Color.BLACK);
                Toast.makeText(this, "Counter number is removed you will start from zero", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}