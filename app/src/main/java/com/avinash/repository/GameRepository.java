package com.avinash.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;


import com.avinash.db.GameDatabase;
import com.avinash.db.entity.Game;

import java.util.List;

public class GameRepository {

    private String DB_NAME = "db_task";

    private GameDatabase noteDatabase;
    public GameRepository(Context context) {
        noteDatabase = Room.databaseBuilder(context, GameDatabase.class, DB_NAME).build();
    }


    public LiveData<Long> insertTask(final Game note) {

        final MutableLiveData<Long> booleanMutableLiveData = new MutableLiveData<>();

        new AsyncTask<Void, Void, Long>() {
            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                booleanMutableLiveData.setValue(aLong);
            }

            @Override
            protected Long doInBackground(Void... voids) {
                return noteDatabase.daoAccess().insertTask(note);
            }
        }.execute();

        return booleanMutableLiveData;
    }


    public LiveData<List<Game>> getTasks() {
        return noteDatabase.daoAccess().fetchAllGames();
    }
}
