package com.avinash.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.avinash.db.dao.GameDao;
import com.avinash.db.entity.Game;


@Database(entities = {Game.class}, version = 1, exportSchema = false)
public abstract class GameDatabase extends RoomDatabase {

    public abstract GameDao daoAccess();
}
