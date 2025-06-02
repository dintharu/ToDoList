package model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ToDoList {
    private String task;
    private  boolean isComplete;
}
