package hr.ferit.mahmutaksakalli.todolist.model;

public class Category {

    private int Id;
    private String Category;

    public Category(int Id, String Category) {
        this.Id = Id;
        this.Category = Category;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }


}