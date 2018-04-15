package hr.ferit.mahmutaksakalli.todolist.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import hr.ferit.mahmutaksakalli.todolist.TodoRepository;
import hr.ferit.mahmutaksakalli.todolist.model.Category;
import hr.ferit.mahmutaksakalli.todolist.model.Todo;

public class TodoListViewModel extends ViewModel {

    TodoRepository mRepository;

    public TodoListViewModel() {
        mRepository = TodoRepository.getInstance();
    }

    /**
     * Methods for get and set Todos
     */

    public LiveData<List<Todo>> getTodoList(){
        return mRepository.getTodos();
    }

    public void insertTodo(Todo todo){
        mRepository.insertTodo(todo);
    }

    public void deleteTodo(Todo todo){
        mRepository.deleteTodo(todo);
    }

    /**
     * Methods for get and set Categories
     */

    public LiveData<List<String>> getCategoryList(){
        return mRepository.getCategories();
    }

    public void insertCategory(Category cat){
        mRepository.insertCategory(cat);
    }

    public void deleteCategory(Category cat){
        mRepository.deleteCategory(cat);
    }
}
