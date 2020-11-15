package com.example.milan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.milan.inscription.NomUtilisateurActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonInscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiserWidjets();
    }

    private void initialiserWidjets() {
        buttonInscription = findViewById(R.id.buttonInscription);

        buttonInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), NomUtilisateurActivity.class));
            }
        });
    }
}