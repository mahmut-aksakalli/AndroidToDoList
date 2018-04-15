package hr.ferit.mahmutaksakalli.todolist.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.ferit.mahmutaksakalli.todolist.R;
import hr.ferit.mahmutaksakalli.todolist.model.Category;
import hr.ferit.mahmutaksakalli.todolist.model.Todo;
import hr.ferit.mahmutaksakalli.todolist.viewmodel.TodoListViewModel;

public class MainActivity extends AppCompatActivity {

    public static final String[] TASK_PRIORITY = new String[]{"LOW","NORMAL","HIGH"};
    public static final String  RETURN_CAT   = "return_cat";
    public static final String  RETURN_TASK  = "return_task";
    public static final String  RETURN_TASK_CAT  = "return_task_cat";
    public static final String  RETURN_PRIORITY  = "return_priority";

    public static final int  CAT_INTENT_KEY  = 90;
    public static final int  TODO_INTENT_KEY = 91;

    @BindView(R.id.newCategory) Button newCategory;
    @BindView(R.id.newTask)     Button newTask;
    @BindView(R.id.rvTodoList)  RecyclerView rvTodos;

    private TodoListViewModel mTodoListViewModel;

    private TodoClickCallback mOnTodoClickListener = new TodoClickCallback() {
        @Override
        public void onClick(Todo note) {
            String message = note.getTodo();
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(Todo note) {
            mTodoListViewModel.deleteTodo(note);
            return true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mTodoListViewModel = ViewModelProviders.of(this)
                .get(TodoListViewModel.class);

        this.setUpRecyclerView();

    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayout =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        DividerItemDecoration divider =
                new DividerItemDecoration(this, linearLayout.getOrientation());

        TodoAdapter adapter = new TodoAdapter(
                new ArrayList<Todo>(), mOnTodoClickListener);

        rvTodos.setLayoutManager(linearLayout);
        rvTodos.addItemDecoration(divider);
        rvTodos.setAdapter(adapter);

        mTodoListViewModel.getTodoList().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(@Nullable List<Todo> todos) {
                ((TodoAdapter)(rvTodos.getAdapter())).refreshData(todos);
            }
        });
    }

    @OnClick(R.id.newCategory)
    public void newCategoryOnClick(){
        Intent categoryIntent = new Intent(this, NewCategoryActivity.class);
        startActivityForResult(categoryIntent, CAT_INTENT_KEY);
    }

    @OnClick(R.id.newTask)
    public void setNewTaskOnClick() {
        Intent taskIntent = new Intent(this, NewTaskActivity.class);
        startActivityForResult(taskIntent, TODO_INTENT_KEY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(resultCode, resultCode, data);

        Random generate = new Random();

        if(resultCode == RESULT_OK){
            switch (requestCode)
            {
                case CAT_INTENT_KEY:

                    int catId = generate.nextInt();
                    String returnCat = data.getExtras().getString(RETURN_CAT);

                    mTodoListViewModel.insertCategory(new Category(catId, returnCat));

                    break;

                case TODO_INTENT_KEY:

                    int taskId = generate.nextInt();
                    String returnTask = data.getExtras().getString(RETURN_TASK);
                    String returnTaskCat = data.getExtras().getString(RETURN_TASK_CAT);
                    String returnPriority = data.getExtras().getString(RETURN_PRIORITY);

                    Todo newTodo = new Todo(taskId, returnTask, returnTaskCat, returnPriority);

                    mTodoListViewModel.insertTodo(newTodo);

                    break;
            }
        }
    }
}
