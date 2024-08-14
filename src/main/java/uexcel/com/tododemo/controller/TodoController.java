package uexcel.com.tododemo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uexcel.com.tododemo.todoservice.ToDo;
import uexcel.com.tododemo.todoservice.TodosService;

import java.net.URI;
import java.util.List;

@RestController
public class TodoController {
    private static final Logger log = LoggerFactory.getLogger(TodoController.class);
    private final TodosService todosService;

    public TodoController(TodosService todosService) {
        this.todosService = todosService;
    }

    @PostMapping("users/{username}/todos")
    public ResponseEntity<String> createTodo(@RequestBody ToDo todo,
                                             @PathVariable String username) {
        todo.setUsername(username);
        ToDo createdTodo = todosService.createTodo(todo);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

        return ResponseEntity.created(location).build();
    };

    @GetMapping("users/{username}/todos")
    public List<ToDo> retrieveUserTodos(@PathVariable String username) {
        return todosService.findByUserName(username);
    }

    @GetMapping("users/{username}/todos/{id}")
    public ToDo retrieveUserTodoById(@PathVariable String username, @PathVariable int id) {
        return todosService.findTodoById(id).orElseThrow();
    }

    @DeleteMapping("users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable String username, @PathVariable int id) {
        todosService.removeTodoById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("users/{username}/todos/{id}")
    public ResponseEntity<Void> updateUserTodo(@RequestBody ToDo toDo,
                                               @PathVariable String username, @PathVariable int id) {
        toDo.setId(id);
        todosService.updateTodo(toDo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("users/todos/{id}")
    public ResponseEntity<Void> todoDone(@PathVariable int id) {
        log.info("{}","**********************************************************");
        todosService.todoDone(id);
        return ResponseEntity.noContent().build();
    }

}
