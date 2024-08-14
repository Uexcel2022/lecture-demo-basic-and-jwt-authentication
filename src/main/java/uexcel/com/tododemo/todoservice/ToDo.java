package uexcel.com.tododemo.todoservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor @ToString
public class ToDo {
    private int id;
    private String username;
    private String description;
    private LocalDate targetDate;
    private boolean status;

}
