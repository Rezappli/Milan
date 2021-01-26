package com.example.milan.ui.roomchat;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.transition.Scene;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.milan.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubRoomFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class SubRoomFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static SubRoom currentRoom;
    private Scene scene1;
    private Scene scene2;
    private Scene currentScene;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubRoomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubRoomFragment newInstance(String param1, String param2) {
        SubRoomFragment fragment = new SubRoomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SubRoomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_sub_room, container, false);
        ImageView addPostImg = root.findViewById(R.id.add_post_img);
        TextView testText = root.findViewById(R.id.textViewTest);
        switch (currentRoom){
            case IT:testText.setText("it");
                break;
            case CAR:testText.setText("car");
                break;
            case MEET:testText.setText("meet");
                break;
            case MOVIE:testText.setText("movie");
                break;
            case GAME:testText.setText("game");
                break;
            case TRAVEL:testText.setText("travel");
                break;
            case FOOD:testText.setText("food");
                break;
        }
        /*scene1 = Scene.getSceneForLayout(root.findViewById(R.id.scene1_add_post), R.layout.fragment_sub_room,getContext());
        scene2 = Scene.getSceneForLayout(root.findViewById(R.id.scene2), R.layout.fragment_add_post,getContext());
        scene1.enter();
        currentScene = scene1;
        Transition transition = TransitionInflater.from(getContext()).inflateTransition(R.transition.transition1);
        addPostImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentScene == scene1)
                    TransitionManager.go(scene2, transition);
            }
        });*/
        return root;
    }
}