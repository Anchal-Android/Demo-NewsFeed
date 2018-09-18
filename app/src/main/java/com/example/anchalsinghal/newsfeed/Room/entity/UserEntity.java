package com.example.anchalsinghal.newsfeed.Room.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import java.sql.Blob;

@Entity(tableName = "newsFeedTable",primaryKeys = "id")
public class UserEntity {

    @ColumnInfo(name="id")
    public int id;

    @ColumnInfo(name = "title")

    public String title = "";

    @ColumnInfo(name = "description")
    public String description = "";

    @ColumnInfo(name = "imageurl")
    public String imageUrl;

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
