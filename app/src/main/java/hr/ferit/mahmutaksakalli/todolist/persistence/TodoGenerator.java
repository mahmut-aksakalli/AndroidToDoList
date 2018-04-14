package hr.ferit.mahmutaksakalli.todolist.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hr.ferit.mahmutaksakalli.todolist.model.Todo;

public class TodoGenerator{

    private static final Random generator = new Random();

    public static Todo generate(){
        int id = generator.nextInt();
        String note = TODOS[generator.nextInt(TODOS.length)];
        String category = CATEGORY[generator.nextInt(CATEGORY.length)];
        String priority = TASK_PRIORITY[generator.nextInt(TASK_PRIORITY.length)];
        return new Todo(id, note, category, priority);
    }

    public static List<Todo> generate(int count){
        List<Todo> todos = new ArrayList<Todo>();
        for (int i=0; i<count; i++){
            todos.add(TodoGenerator.generate());
        }
        return todos;
    }

    private static final String[] TODOS = new String[]{
            "Priest Of The River",
            "Butterfly Of The Gods",
            "Children Without Desire",
            "Spiders Of The Banished",
            "Hunters And Rats",
            "Heirs And Doctors",
            "Misfortune Without A Conscience",
            "Argument Of The North",
            "Rescue At The Elements",
            "Whispers Of The South",
            "Officer Of The Past",
            "Dog In My Garden",
            "Girls Of An Asteroid",
            "Mice Of The Swamp",
            "Witches And Rebels",
            "Women And Mice",
            "Prediction In My City",
            "Question Of The River",
            "Help Of The South",
            "Moral Of The Cliffs"
    };

    private static final String[] CATEGORY = new String[]{
            "daily",
            "business",
            "school",
            "payments"
    };

    private static final String[] TASK_PRIORITY = new String[]{"LOW","NORMAL","HIGH"};


}