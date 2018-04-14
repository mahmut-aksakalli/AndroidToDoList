package hr.ferit.mahmutaksakalli.todolist.persistence;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hr.ferit.mahmutaksakalli.todolist.model.Todo;

public class FakeDatabase{

    private static final int COUNT = 15;
    private static final String TAG = "Creation";

    private List<Todo> Todos;

    private static FakeDatabase sInstance;

    private FakeDatabase(){

        Todos = new ArrayList<>();
        Todos.addAll(TodoGenerator.generate(COUNT));
        Log.d(TAG, "Created new data.");
    }

    public static FakeDatabase getInstance(){
        if(sInstance == null){
            sInstance = new FakeDatabase();
        }
        return sInstance;
    }

    public List<Todo> getTodos(){
        return Todos;
    }

    public void insertTodo() {
        Todo note = TodoGenerator.generate();
        Todos.add(0, note);
    }

    public void delete(Todo note) {
        int id = note.getId();
        for (int i = Todos.size()-1; i>=0; i--){
            if(Todos.get(i).getId() == id){
                Todos.remove(i);
            }
        }
    }

}