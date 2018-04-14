package hr.ferit.mahmutaksakalli.todolist.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.ferit.mahmutaksakalli.todolist.R;

public class NewTaskActivity extends AppCompatActivity {

    public ArrayAdapter<String> priorityAdapter;

    @BindView(R.id.nameTask) EditText nameTask;
    @BindView(R.id.PrioritySpinner) Spinner prioritySpinner;
    @BindView(R.id.CategorySpinner) Spinner categorySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        ButterKnife.bind(this);

        priorityAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, MainActivity.TASK_PRIORITY);
        prioritySpinner.setAdapter(priorityAdapter);

    }

    @OnClick(R.id.addTask)
    public void addTaskOnClick() {
        nameTask.setText("asd");
    }

    @OnClick(R.id.nameTask)
    public void onEditTextClick(){
        nameTask.setText("");
    }

}
