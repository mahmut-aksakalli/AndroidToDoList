package hr.ferit.mahmutaksakalli.todolist.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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

public class NewTaskActivity extends AppCompatActivity {

    public ArrayAdapter<String> priorityAdapter;
    public ArrayAdapter<String> categoryAdapter;

    @BindView(R.id.nameTask) EditText nameTask;
    @BindView(R.id.PrioritySpinner) Spinner prioritySpinner;
    @BindView(R.id.CategorySpinner) Spinner categorySpinner;

    private TodoListViewModel mTodoListViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        ButterKnife.bind(this);

        mTodoListViewModel = ViewModelProviders.of(this)
                .get(TodoListViewModel.class);

        priorityAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, MainActivity.TASK_PRIORITY);
        prioritySpinner.setAdapter(priorityAdapter);

        categoryAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, new ArrayList<String>());
        categorySpinner.setAdapter(categoryAdapter);

        mTodoListViewModel.getCategoryList().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> cats) {
                categoryAdapter.clear();
                categoryAdapter.addAll(cats);
                categoryAdapter.notifyDataSetChanged();
            }
        });

    }

    @OnClick(R.id.addTask)
    public void addTaskOnClick() {
        String note = nameTask.getText().toString();
        String priority = prioritySpinner.getSelectedItem().toString();
        String cat = categorySpinner.getSelectedItem().toString();

        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.RETURN_TASK, note);
        returnIntent.putExtra(MainActivity.RETURN_TASK_CAT, cat);
        returnIntent.putExtra(MainActivity.RETURN_PRIORITY, priority);
        setResult(RESULT_OK, returnIntent);
        this.finish();
    }

    @OnClick(R.id.nameTask)
    public void onEditTextClick(){
        nameTask.setText("");
    }

}
