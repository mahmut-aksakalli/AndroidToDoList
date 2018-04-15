package hr.ferit.mahmutaksakalli.todolist.persistence.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import hr.ferit.mahmutaksakalli.todolist.model.Category;
import hr.ferit.mahmutaksakalli.todolist.model.Todo;

@Dao
public interface TodoDao{

    @Query("SELECT * FROM Todo")
    LiveData<List<Todo>> getTodos();

    @Query("SELECT * FROM Todo WHERE Category = :category")
    LiveData<List<Todo>> getTodosBy(String category);

    @Query("SELECT * FROM Category")
    LiveData<List<Category>> getCategories();

    @Query("SELECT * FROM Category WHERE category=:category")
    LiveData<List<Category>> checkCategory();

    @Delete
    void deleteTodo(Todo todo);

    @Delete
    void deleteCategory(Category category);

    @Insert
    void insertBook(Todo todo);

    @Insert
    void insertCategory(Category category);
}