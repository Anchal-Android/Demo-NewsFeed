package com.example.anchalsinghal.newsfeed.Room.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.example.anchalsinghal.newsfeed.Room.entity.UserEntity;

import java.util.List;


@Dao
public interface UserDao {

    @Insert
     void insertUserData(UserEntity userEntityList);

   /* @Query("UPDATE usertable SET firstName = :firstname, lastname = :lastname")
     void updateUserData(String firstname, String lastname);*/

    @Query("SELECT * FROM newsFeedTable")
    List<UserEntity> getUserDetails();

   /* @Query("DELETE FROM usertable")
    void deleteUser();*/

}
