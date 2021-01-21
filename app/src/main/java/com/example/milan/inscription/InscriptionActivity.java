package com.example.milan.inscription;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.milan.R;
import com.example.milan.objects.User;

public class InscriptionActivity extends AppCompatActivity {

    public static User registerUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        registerUser = new User();
    }
}