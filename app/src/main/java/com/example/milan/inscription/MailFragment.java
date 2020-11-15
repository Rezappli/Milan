package com.example.milan.inscription;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.milan.R;

public class MailFragment extends Fragment {

    private Button buttonNext;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_inscription_mail, container, false);

        buttonNext = root.findViewById(R.id.buttonNext2);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_inscription);


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_nav_mail_to_nav_tel);
            }
        });

        return root;


    }


}