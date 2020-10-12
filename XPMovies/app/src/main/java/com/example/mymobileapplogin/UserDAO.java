package com.example.mymobileapplogin;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao //Room interface annotation.
public interface UserDAO {
    //methods created within interface do not need to have a method body

    @Insert //Room insert annotation
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
