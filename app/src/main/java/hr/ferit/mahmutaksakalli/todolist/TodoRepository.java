package hr.ferit.mahmutaksakalli.todolist;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import hr.ferit.mahmutaksakalli.todolist.model.Category;
import hr.ferit.mahmutaksakalli.todolist.model.Todo;
import hr.ferit.mahmutaksakalli.todolist.persistence.room.TodoDao;
import hr.ferit.mahmutaksakalli.todolist.persistence.room.TodoDatabase;

public class TodoRepository {
    private static TodoRepository sInstance;

    private TodoDatabase mDatabase;
    private LiveData<List<Todo>> mData;
    private LiveData<List<String>> mCategory;

    private TodoRepository(Application application){
        mDatabase = TodoDatabase.getInstance(application);
        mData = mDatabase.todoDao().getTodos();
        mCategory = mDatabase.todoDao().getCategories();
    }

    public static TodoRepository getInstance(){
        if(sInstance == null){
            sInstance = new TodoRepository(TodoApp.getInstance());
        }
        return sInstance;
    }

    /**
     * Methods for Async Todos methods
     */

    public LiveData<List<Todo>> getTodos(){
        return mData;
    }

    public void insertTodo(Todo newTodo) {
        // Use a separate thread to access the database since it could take a while.
        new insertTodoAsyncTask(mDatabase.todoDao()).execute(newTodo);
    }

    public void deleteTodo(Todo todo) {
        new deleteTodoTask(mDatabase.todoDao()).execute(todo);
    }

    private class insertTodoAsyncTask extends AsyncTask<Todo,Void,Void> {

        private TodoDao mTodoDao;

        public insertTodoAsyncTask(TodoDao TodoDao) {
            mTodoDao = TodoDao;
        }

        @Override
        protected Void doInBackground(Todo... Todos) {
            mTodoDao.insertTodo(Todos[0]);
            return null;
        }
    }

    private class deleteTodoTask extends AsyncTask<Todo, Void, Void>{
        private TodoDao mTodoDao;

        public deleteTodoTask(TodoDao TodoDao) {
            this.mTodoDao = TodoDao;
        }

        @Override
        protected Void doInBackground(Todo... Todos) {
            mTodoDao.deleteTodo(Todos[0]);
            return null;
        }
    }

    /**
     * Methods for Async Category methods
     */

    public LiveData<List<String>> getCategories(){
        return mCategory;
    }

    public void insertCategory(Category newCat) {
        // Use a separate thread to access the database since it could take a while.
        new insertCategoryAsyncTask(mDatabase.todoDao()).execute(newCat);
    }

    public void deleteCategory(Category cat) {
        new deleteTodoCategory(mDatabase.todoDao()).execute(cat);
    }

    private class insertCategoryAsyncTask extends AsyncTask<Category,Void,Void> {

        private TodoDao mTodoDao;

        public insertCategoryAsyncTask(TodoDao TodoDao) {
            mTodoDao = TodoDao;
        }

        @Override
        protected Void doInBackground(Category... cats) {
            mTodoDao.insertCategory(cats[0]);
            return null;
        }
    }

    private class deleteTodoCategory extends AsyncTask<Category, Void, Void>{
        private TodoDao mTodoDao;

        public deleteTodoCategory(TodoDao TodoDao) {
            this.mTodoDao = TodoDao;
        }

        @Override
        protected Void doInBackground(Category... cats) {
            mTodoDao.deleteCategory(cats[0]);
            return null;
        }
    }
}
