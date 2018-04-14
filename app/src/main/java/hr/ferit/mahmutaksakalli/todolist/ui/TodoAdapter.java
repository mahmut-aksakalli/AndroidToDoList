package hr.ferit.mahmutaksakalli.todolist.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import hr.ferit.mahmutaksakalli.todolist.R;
import hr.ferit.mahmutaksakalli.todolist.model.Todo;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder>{

    private List<Todo> Todos;
    private TodoClickCallback mCallback;

    public TodoAdapter(List<Todo> Todos, TodoClickCallback onTodoClickListener){
        this.Todos = new ArrayList<>();
        this.refreshData(Todos);
        this.mCallback = onTodoClickListener;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view, mCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo current = Todos.get(position);
        holder.note.setText(current.getTodo());
        holder.category.setText(current.getCategory());
        if(current.getPriority().equals(MainActivity.TASK_PRIORITY[0])) {
            holder.priority.setImageResource(R.drawable.circle_green);
            holder.category.setTextColor(Color.parseColor("#43A047"));
        }
        else if(current.getPriority().equals(MainActivity.TASK_PRIORITY[1])) {
            holder.priority.setImageResource(R.drawable.circle_blue);
            holder.category.setTextColor(Color.parseColor("#1E88E5"));
        }
        else if (current.getPriority().equals(MainActivity.TASK_PRIORITY[2])) {
            holder.priority.setImageResource(R.drawable.circle_red);
            holder.category.setTextColor(Color.parseColor("#E53935"));
        }

    }

    @Override
    public int getItemCount() {
        return this.Todos.size();
    }

    public void refreshData(List<Todo> Todos) {
        this.Todos.clear();
        this.Todos.addAll(Todos);
        this.notifyDataSetChanged();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.priority) ImageView priority;
        @BindView(R.id.note)     TextView note;
        @BindView(R.id.category) TextView category;

        public TodoViewHolder(View itemView, final TodoClickCallback callback) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onClick(Todos.get(getAdapterPosition()));
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return callback.onLongClick(Todos.get(getAdapterPosition()));
                }
            });
        }
    }


}
