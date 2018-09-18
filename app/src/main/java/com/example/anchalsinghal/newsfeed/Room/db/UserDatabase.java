package com.example.anchalsinghal.newsfeed.Room.db;


import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


import com.example.anchalsinghal.newsfeed.Room.dao.UserDao;
import com.example.anchalsinghal.newsfeed.Room.entity.UserEntity;


@Database(entities = UserEntity.class, version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase DB_INSTANCE;

    protected static String DB_NAME = "userdb.db";

    public abstract UserDao getUserDao();


    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    public static UserDatabase getDbInstance(Context context)
    {

        if(DB_INSTANCE == null)
        {
            DB_INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class,DB_NAME).fallbackToDestructiveMigration().build();
        }


        return  DB_INSTANCE;
    }

}
