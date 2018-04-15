package hr.ferit.mahmutaksakalli.todolist.persistence.room;


import android.app.Application;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import hr.ferit.mahmutaksakalli.todolist.model.Category;
import hr.ferit.mahmutaksakalli.todolist.model.Todo;

@Database(entities = {Todo.class, Category.class}, version = 1)
public abstract class TodoDatabase extends RoomDatabase{

    private static TodoDatabase sInstance;
    private static final String DATABASE_NAME = "todolist.db";

    public static TodoDatabase getInstance(Application application){
        if(sInstance == null){
            sInstance = Room.databaseBuilder(
                    application.getApplicationContext(),
                    TodoDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return sInstance;
    }

    // Database access objects:
    public abstract TodoDao todoDao();
}