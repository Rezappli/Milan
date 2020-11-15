package com.example.milan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.milan.inscription.InscriptionActivity;
import com.example.milan.inscription.NomUtilisateurFragment;
import com.google.android.gms.common.SignInButton;

public class MainActivity extends AppCompatActivity {

    private Button buttonInscription;
    private SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_ICON_ONLY);
        signInButton.setColorScheme(SignInButton.COLOR_AUTO);

        initialiserWidjets();
    }

    private void initialiserWidjets() {
        buttonInscription = findViewById(R.id.buttonInscription);

        buttonInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), InscriptionActivity.class));
            }
        });
    }
}