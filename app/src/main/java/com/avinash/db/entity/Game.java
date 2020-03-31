package com.avinash.db.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;


import java.io.Serializable;
import java.util.Date;


@Entity
public class Game implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String answerOne;
    public String answerTwo;


    @ColumnInfo(name = "created_at")
    public Long createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
