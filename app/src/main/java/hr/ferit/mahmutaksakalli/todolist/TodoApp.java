package hr.ferit.mahmutaksakalli.todolist;

import android.app.Application;

public class TodoApp extends Application {

    private static TodoApp sInstance;

    public static TodoApp getInstance(){
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }


}