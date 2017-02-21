package com.grouptwosoftworks.chukbooks;

/**
 * Created by MSI DRAGON on 2/13/2017.
 */

public class Book {

    public static enum CATEGORY{All,Child,DnD, TEXT_BOOK}

    private final int name;
    private final int author;
    private final int imageResource;
    private CATEGORY category;
    private boolean isFavorite = false;
    private final String imageUrl;

    public Book(int name, int author, int imageResource, CATEGORY category,String imageUrl) {
        this.name = name;
        this.author = author;
        this.imageResource = imageResource;
        this.category = category;
        this.imageUrl = imageUrl;
    }



    public int getName() {
        return name;
    }

    public int getAuthor() {
        return author;
    }

    public int getImageResource() {
        return imageResource;
    }

    public CATEGORY getCategory(){return category;}

    public boolean getIsFavorite() {
        return isFavorite;
    }
    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public void toggleFavorite() {
        isFavorite = !isFavorite;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
