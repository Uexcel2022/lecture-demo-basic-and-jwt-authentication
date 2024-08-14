package uexcel.com.tododemo.todoservice;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class TodosService {
    private static final List<ToDo> taskList;

    static {
        taskList = new ArrayList<>();
        taskList.add(new ToDo(1, "uexcel","Java",LocalDate.now(),false));
        taskList.add(new ToDo(2, "uexcel","Microservices",LocalDate.now(), false));
        taskList.add(new ToDo(3, "uexcel","React",LocalDate.now(),false));
    }

    public List<ToDo>  findByUserName(String username){
        Predicate<ToDo> predicate = task->task.getUsername().equalsIgnoreCase(username);
        return taskList.stream().filter(predicate).toList();
    }

    public ToDo createTodo(ToDo toDo){
        taskList.stream().mapToInt(ToDo::getId).max().ifPresent(id -> toDo.setId(id+1));
        taskList.add(toDo);
        return toDo;
    }

    public Optional<ToDo> findTodoById(int todoId){
        Predicate<ToDo> predicate = toDo -> toDo.getId() == todoId;
        return Optional.ofNullable(taskList.stream().filter(predicate).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Not Found ID:"+todoId)));
    }

    public void removeTodoById(int todoId){
//        Predicate<ToDo> predicate = task -> task.getId() == todoId;
//        findTodoById(todoId);
        taskList.remove(findTodoById(todoId).orElseThrow());

    }

    public void updateTodo(ToDo todo) {
      ToDo toUpdatetoDo =  taskList.stream().filter(toDos->toDos.getId()==todo.getId()).findFirst().orElseThrow();

      if(todo.getUsername()!=null && !todo.getUsername().isBlank() && !todo.getUsername().isEmpty()) {
          toUpdatetoDo.setUsername(todo.getUsername());
      }
        if(todo.getDescription()!=null && !todo.getDescription().isBlank() && !todo.getDescription().isEmpty()) {
            toUpdatetoDo.setDescription(todo.getDescription());
        }

        if(todo.getTargetDate()!=null) {
            toUpdatetoDo.setTargetDate(todo.getTargetDate());
        }
    }

    public void todoDone(int todoId){
        taskList.stream().filter(todos->todos.getId()==todoId).findFirst().ifPresent(todo -> {
            todo.setStatus(toggleTaskStatus(todo.isStatus()));
        });
//        for(ToDo obj : taskList) {
//            if (obj.getId() == taskId) {
//                obj.setStatus(toggleTaskStatus(obj.isStatus()));
//                return;
//            }
//        }
    }

    public boolean toggleTaskStatus(boolean taskStatus){
        return !taskStatus;
    }
}

