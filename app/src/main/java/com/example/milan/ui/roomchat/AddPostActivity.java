package com.example.milan.ui.roomchat;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.milan.R;
import com.example.milan.bdd.BddPost;
import com.example.milan.objects.Post;
import com.example.milan.ui.roomchat.enums.SubRoom;
import com.example.milan.ui.roomchat.enums.TypePost;
import com.google.android.material.textfield.TextInputEditText;

import static com.example.milan.utils.Constants.currentUser;

public class AddPostActivity extends AppCompatActivity {

    private CardView changeRoom, addImage;
    private ImageView imagePost, addPost;
    static final int RESULT_LOAD_IMG = 1;
    private boolean hasSelectedImage = false;
    private Uri imageUri = null;
    private TextView textViewCurrentRoom;
    private TextInputEditText messagePost;
    public static SubRoom currentRoom;
    private boolean hasPhoto;
    private boolean hasVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        changeRoom = findViewById(R.id.changeRoomPost);
        addImage = findViewById(R.id.addImagePost);
        imagePost = findViewById(R.id.imagePost);
        addPost = findViewById(R.id.addPost);
        messagePost = findViewById(R.id.messagePost);
        textViewCurrentRoom = findViewById(R.id.textViewRoomPost);
        initialiseCurrentRoom();
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Enter into the user's pictures
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });


        changeRoom.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),ChangeRoomPostActivity.class));
                overridePendingTransition(R.anim.slide_up,R.anim.slide_bottom);
            }
        });

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPost();
                onBackPressed();
            }
        });
    }

    private void uploadPost() {
        TypePost tp;
        if(hasPhoto){
            tp = TypePost.PHOTO;
        }else if(hasVideo){
            tp = TypePost.VIDEO;
        }else{
            tp = TypePost.TEXT;
        }
        Post post = new Post(currentUser.getUsername(),messagePost.getText().toString(),currentRoom,tp);
        BddPost bdd = new BddPost();
        bdd.uploadPost(post);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initialiseCurrentRoom() {
        Drawable img;
        switch (currentRoom){
            case CAR:img = this.getResources().getDrawable(R.drawable.room_car);
                break;
            case IT:img = this.getResources().getDrawable(R.drawable.room_it);
                break;
            case FOOD:img = this.getResources().getDrawable(R.drawable.room_food);
                break;
            case GAME:img = this.getResources().getDrawable(R.drawable.cadenas_black);
                break;
            case MEET:img = this.getResources().getDrawable(R.drawable.room_meet);
                break;
            case SHOP:img = this.getResources().getDrawable(R.drawable.room_shop);
                break;
            case WORK:img = this.getResources().getDrawable(R.drawable.room_work);
                break;
            case MOVIE:img = this.getResources().getDrawable(R.drawable.room_movies);
                break;
            case MUSIC:img = this.getResources().getDrawable(R.drawable.room_music);
                break;
            case SPORT:img = this.getResources().getDrawable(R.drawable.room_sport);
                break;
            case STUDY:img = this.getResources().getDrawable(R.drawable.room_study);
                break;
            case TRAVEL:img = this.getResources().getDrawable(R.drawable.room_travel);
                break;
           /* case CONCERT:img = this.getResources().getDrawable(R.drawable.room_car);
                break;*/
            default:
                throw new IllegalStateException("Unexpected value: " + currentRoom);
        }

        img.setBounds(0, 0, 60, 60);
        textViewCurrentRoom.setCompoundDrawables(null, null, null, img);
    }

    // When the user choose his picture inside his phone
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            imageUri = data.getData();
            assert imageUri != null;
            // Display into the ImageView the picture choosed by the user
            Glide.with(this).load(imageUri).fitCenter().into(imagePost);
            hasSelectedImage = true;
        }else {
            Toast.makeText(getApplicationContext(),"Vous n'avez pas choisi d'image", Toast.LENGTH_LONG).show();
        }
    }

}