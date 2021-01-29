package com.example.milan.ui.socialchat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.milan.R;
import com.example.milan.ui.actualite.SubActualiteFragment;
import com.example.milan.ui.actualite.enums.SubRoom;

public class SocialFragment extends Fragment {


    NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sub_social, container, false);
        CardView room_it = root.findViewById(R.id.room_it);
        CardView room_car = root.findViewById(R.id.room_car);
        CardView room_food = root.findViewById(R.id.room_food);
        CardView room_meet = root.findViewById(R.id.room_meet);
        CardView room_movie = root.findViewById(R.id.room_movie);
        CardView room_music = root.findViewById(R.id.room_music);
        CardView room_shop = root.findViewById(R.id.room_shop);
        CardView room_study = root.findViewById(R.id.room_study);
        navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);

        room_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSubRoom(SubRoom.IT);
            }
        });

        room_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSubRoom(SubRoom.CAR);
            }
        });

        room_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSubRoom(SubRoom.FOOD);
            }
        });

        room_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSubRoom(SubRoom.MOVIE);
            }
        });

        room_meet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSubRoom(SubRoom.MEET);
            }
        });


        return root;
    }

    public void goSubRoom(SubRoom sb){
        SubActualiteFragment.currentRoom = sb;
        navController.navigate(R.id.action_nav_social_to_nav_sub_social);
    }
}