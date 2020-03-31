package com.avinash.db.dao;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.avinash.db.entity.Game;

import java.util.List;

@Dao
public interface GameDao {

    @Insert
    Long insertTask(Game note);

    @Query("SELECT * FROM Game ORDER BY created_at desc")
    LiveData<List<Game>> fetchAllGames();

}
