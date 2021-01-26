package com.example.milan.inscription;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.example.milan.HomeActivity;
import com.example.milan.HomeActivity;
import com.example.milan.MainActivity;
import com.example.milan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.io.IOException;

import static com.example.milan.utils.Constants.mAuth;
import static com.example.milan.utils.Constants.mStoreBase;

public class MotDePasseFragment extends Fragment {

    private Button buttonNext;

    private EditText editTextPassword;
    private EditText editTextConfirmPassword;

    private String contentPassword;
    private String contentConfirmPassword;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_inscription_mot_de_passe, container, false);

        editTextPassword = root.findViewById(R.id.editTextPassword);
        editTextConfirmPassword = root.findViewById(R.id.editTextConfirmPassword);

        buttonNext = root.findViewById(R.id.buttonNext4);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentPassword = editTextPassword.getText().toString();
                contentConfirmPassword = editTextConfirmPassword.getText().toString();
                if (contentPassword.length() >= 6) {
                    if (containsUpperChar(contentPassword) && containsNumber(contentPassword)) {
                        if (contentPassword.equals(contentConfirmPassword)) {
                            signupUser();
                        } else {
                            editTextPassword.setError("Les mots de passe ne correspondent pas !");
                            editTextConfirmPassword.setError("Les mots de passe ne correspondent pas !");
                        }
                    } else {
                        editTextPassword.setError("Le mot de passe doit contenir au moins 1 majuscule et 1 chiffre");
                    }
                } else {
                    editTextPassword.setError("Entrer un mot de passe de 6 caractères au minimum");
                }
            }
        });

        return root;

    }

    private boolean containsUpperChar(String s){
        for(char c : s.toCharArray()){
            if(Character.isUpperCase(c))
                return true;
        }
        return false;
    }

    private boolean containsNumber(String s){
        for(char c : s.toCharArray()){
            try{
                Integer.parseInt(c+"");
                return true;
            }catch (Exception e){}
        }
        return false;
    }

    private void signupUser(){
        mAuth.createUserWithEmailAndPassword(InscriptionActivity.registerUser.getMail(), contentPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    sendMailVerification();
                }else {
                    Log.w("Inscription", task.getException().getMessage());
                    Toast.makeText(getContext(), "Une erreur est survenue !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendMailVerification(){
        mAuth.getCurrentUser().sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Bienvenue sur Milan ! Veuillez valider votre mail !", Toast.LENGTH_SHORT).show();
                            mStoreBase.collection("users").document(InscriptionActivity.registerUser.getUid()).set(InscriptionActivity.registerUser);
                            startActivity(new Intent(getContext(), MainActivity.class));
                        } else {
                            Log.e("MAIL", "sendEmailVerification", task.getException());
                            Toast.makeText(getContext(),
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}