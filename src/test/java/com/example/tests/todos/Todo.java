package com.example.tests.todos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Todo {
    private int id;
    private String text;
    private boolean completed;

    public Todo(int id, String text){
    }
}