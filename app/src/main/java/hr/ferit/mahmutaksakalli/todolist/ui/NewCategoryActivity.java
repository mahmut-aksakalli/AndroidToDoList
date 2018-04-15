package hr.ferit.mahmutaksakalli.todolist.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.ferit.mahmutaksakalli.todolist.R;

public class NewCategoryActivity extends AppCompatActivity {

    @BindView(R.id.nameCategory) EditText nameCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.addCategory)
    public void addCategoryOnClick() {

        String cat = nameCategory.getText().toString();

        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.RETURN_CAT, cat);
        setResult(RESULT_OK, returnIntent);
        this.finish();
    }

    @OnClick(R.id.nameCategory)
    public void onEditTextClick(){
        nameCategory.setText("");
    }
}
