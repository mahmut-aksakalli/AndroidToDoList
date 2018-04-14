package hr.ferit.mahmutaksakalli.todolist.model;

public class Todo {

    private int Id;
    private String Todo;
    private String Category;
    private String Priority;

    public Todo(int Id, String Todo, String Category, String Priority) {
        this.Id = Id;
        this.Todo = Todo;
        this.Category = Category;
        this.Priority = Priority;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTodo() {
        return Todo;
    }

    public void setTodo(String Todo) {
        this.Todo = Todo;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String Priority) {
        this.Priority = Priority;
    }
}