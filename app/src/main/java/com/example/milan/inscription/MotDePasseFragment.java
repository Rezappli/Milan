package com.example.milan.inscription;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//import com.example.milan.HomeActivity;
import com.example.milan.HomeActivity;
import com.example.milan.R;

public class MotDePasseFragment extends Fragment {

    private Button buttonNext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_inscription_mot_de_passe, container, false);

        buttonNext = root.findViewById(R.id.buttonNext4);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(getContext(), HomeActivity.class));
            }
        });

        return root;

    }
}