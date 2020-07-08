package com.example.timestable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String launchValueLock = "activity_one";
    private Button launch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        launch = (Button) findViewById(R.id.launch_button);
        launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity();
            }
        });
    }

    public void launchActivity() {

        TextView tableMaxSizeText = findViewById(R.id.table_intvalue_text);
        /*
        * We would not want to read negative and NAN values.
        * The two situations are being handled.
        * */
        try {
            int valueToPass = Integer.parseInt(tableMaxSizeText.getText().toString());
            if (valueToPass <= 0)
                throw new IllegalArgumentException();

            Intent intent = new Intent(this, TableActivity.class);
            intent.putExtra(launchValueLock, valueToPass);
            startActivity(intent);
        }
        catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Enter a positive number!", Toast.LENGTH_LONG).show();
        }
    }
}