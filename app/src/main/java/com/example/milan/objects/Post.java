package com.example.milan.objects;

import com.example.milan.ui.actualite.enums.SubRoom;
import com.example.milan.ui.actualite.enums.TypePost;

import java.util.UUID;

public class Post {
    private String id;
    private String ownerName;
    private String message;
    private int nbLike;
    private int nbComment;
    private int nbShare;
    private SubRoom room;
    private TypePost type;

    public Post(){

    }
    public Post(String ownerName, String message, SubRoom room, TypePost type) {
        this.id = UUID.randomUUID().toString();
        this.ownerName = ownerName;
        this.message = message;
        this.nbLike = 0;
        this.nbComment = 0;
        this.nbShare = 0;
        this.room = room;
        this.type = type;
    }


    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNbLike() {
        return nbLike;
    }

    public void setNbLike(int nbLike) {
        this.nbLike = nbLike;
    }

    public int getNbComment() {
        return nbComment;
    }

    public void setNbComment(int nbComment) {
        this.nbComment = nbComment;
    }

    public int getNbShare() {
        return nbShare;
    }

    public void setNbShare(int nbShare) {
        this.nbShare = nbShare;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SubRoom getRoom() {
        return room;
    }

    public void setRoom(SubRoom room) {
        this.room = room;
    }

    public TypePost getType() {
        return type;
    }

    public void setType(TypePost type) {
        this.type = type;
    }
}
