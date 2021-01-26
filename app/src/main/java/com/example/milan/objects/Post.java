package com.example.milan.objects;

public class Post {
    private String ownerName;
    private String message;
    private int nbLike;
    private int nbComment;
    private int nbShare;

    public Post(String ownerName, String message, int nbLike, int nbComment, int nbShare) {
        this.ownerName = ownerName;
        this.message = message;
        this.nbLike = nbLike;
        this.nbComment = nbComment;
        this.nbShare = nbShare;
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
}
