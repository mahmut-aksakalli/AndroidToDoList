package hr.ferit.mahmutaksakalli.todolist.ui;

import hr.ferit.mahmutaksakalli.todolist.model.Todo;

public interface TodoClickCallback {
    void onClick(Todo note);
    boolean onLongClick(Todo note);
}
