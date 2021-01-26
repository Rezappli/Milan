package com.example.milan.ui.roomchat;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.Scene;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.milan.R;
import com.example.milan.objects.Post;
import com.example.milan.ui.roomchat.enums.SubRoom;
import com.example.milan.ui.roomchat.enums.SubRoomCollections;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

import static com.example.milan.utils.Constants.mStoreBase;

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
    private RecyclerView mRecyclerView;
    private FirestoreRecyclerAdapter adapter;


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
            case IT:launchPosts(SubRoomCollections.room_it);
                break;
            case CAR:launchPosts(SubRoomCollections.room_car);
                break;
            case MEET:launchPosts(SubRoomCollections.room_meet);
                break;
            case MOVIE:launchPosts(SubRoomCollections.room_movie);
                break;
            case GAME:launchPosts(SubRoomCollections.room_game);
                break;
            case TRAVEL:launchPosts(SubRoomCollections.room_travel);
                break;
            case FOOD:launchPosts(SubRoomCollections.room_food);
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

    private void launchPosts(SubRoomCollections src) {
        Query query = mStoreBase.collection(src.toString());

        FirestoreRecyclerOptions<Post> options = new FirestoreRecyclerOptions.Builder<Post>().setQuery(query, Post.class).build();

        adapter = new FirestoreRecyclerAdapter<Post, ViewHolder>(options) {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final ViewHolder userViewHolder, int i, @NonNull final Post p) {

            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        adapter.startListening();
    }


    private class ViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private TextView message;
    private ImageView image;
    private ImageView like;
    private ImageView comment;
    private ImageView share;
    private ImageView postImage;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);


    }
    }
}