package com.example.milan.inscription;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import com.example.milan.HomeActivity;
import com.example.milan.HomeActivity;
import com.example.milan.R;

public class MotDePasseActivity extends AppCompatActivity {

    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_mot_de_passe);
        buttonNext = findViewById(R.id.buttonNext4);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(getBaseContext(), HomeActivity.class));
            }
        });

    }
}