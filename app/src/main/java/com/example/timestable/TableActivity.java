package com.example.timestable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        Intent intent = getIntent();
        //Maximum value is passed in from the main activity and in case of a launch from a different
        //activity, the initialization value will be 10;
        final int DEFAULT = 10;
        final int TABLE_MAX = intent.getIntExtra(MainActivity.launchValueLock, DEFAULT);
        final TextView valueText = findViewById(R.id.value_display);
        final ListView valueList = findViewById(R.id.listView);
        final SeekBar valueChanger = findViewById(R.id.seekBar);
        valueChanger.setMax(TABLE_MAX);

        valueChanger.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int currentTableValue;
                int minimum = 1;
                //We would like to not let the seek bar jump to zero.
                if (progress < minimum) {
                    currentTableValue = minimum;
                    valueChanger.setProgress(currentTableValue);
                } else {
                    currentTableValue = progress;
                }

                valueText.setText("seeing values for number: " + TABLE_MAX);
                ArrayList<String> values = new ArrayList<>();
                for (int i = minimum; i <= TABLE_MAX; i++)
                    values.add(i + " x " + currentTableValue + " = " + Integer.toString(i * currentTableValue));

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, values);
                valueList.setAdapter(adapter);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /*
    * To kill the activity.
    * */
    public void back(View view) {
        finish();
    }
}