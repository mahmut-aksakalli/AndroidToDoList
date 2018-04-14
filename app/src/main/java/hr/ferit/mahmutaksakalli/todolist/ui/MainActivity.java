package hr.ferit.mahmutaksakalli.todolist.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.ferit.mahmutaksakalli.todolist.R;
import hr.ferit.mahmutaksakalli.todolist.model.Todo;
import hr.ferit.mahmutaksakalli.todolist.persistence.FakeDatabase;

public class MainActivity extends AppCompatActivity {

    public static final String[] TASK_PRIORITY = new String[]{"LOW","NORMAL","HIGH"};

    @BindView(R.id.newCategory) Button newCategory;
    @BindView(R.id.newTask)     Button newTask;
    @BindView(R.id.rvTodoList)  RecyclerView rvTodos;

    private TodoClickCallback mOnTodoClickListener = new TodoClickCallback() {
        @Override
        public void onClick(Todo note) {
            String message = note.getTodo();
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(Todo note) {
            FakeDatabase.getInstance().delete(note);
            ((TodoAdapter)(rvTodos.getAdapter())).refreshData(FakeDatabase.getInstance().getTodos());
            return true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.setUpRecyclerView();

    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayout =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        DividerItemDecoration divider =
                new DividerItemDecoration(this, linearLayout.getOrientation());

        TodoAdapter adapter = new TodoAdapter(
                FakeDatabase.getInstance().getTodos(), mOnTodoClickListener);

        rvTodos.setLayoutManager(linearLayout);
        rvTodos.addItemDecoration(divider);
        rvTodos.setAdapter(adapter);
    }

    @OnClick(R.id.newCategory)
    public void newCategoryOnClick(){
        startActivity(new Intent(this, NewCategoryActivity.class));
    }

    @OnClick(R.id.newTask)
    public void setNewTaskOnClick(){
        startActivity(new Intent(this, NewTaskActivity.class));
    }
}
