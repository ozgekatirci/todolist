package com.ozge.todolistapp.controller;

import com.ozge.todolistapp.dto.RequestDto.TodoRequestDto;
import com.ozge.todolistapp.dto.ResponseDto.TodoResponseDto;
import com.ozge.todolistapp.dto.ResponseDto.UserResponseDto;
import com.ozge.todolistapp.entity.Todo;
import com.ozge.todolistapp.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@AllArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/save")
    public ResponseEntity<UserResponseDto> saveTodo(@RequestParam Long userId, @RequestBody TodoRequestDto todoRequestDto){
        UserResponseDto userResponseDTO= todoService.saveTodoByUserId(userId, todoRequestDto);
        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TodoResponseDto>>findUserAllTodo(@RequestParam Long userId){
        List<TodoResponseDto> todoList = todoService.findUserAllTodo(userId);
        return ResponseEntity.ok(todoList);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String>deleteTodoFromUserbyTodoID(@RequestParam Long userId, @RequestParam int index){
        todoService.removeTodoFromUser(userId,index);
        return ResponseEntity.ok("Todo is deleted");
    }
    @GetMapping("/daily")
    public ResponseEntity<List<TodoResponseDto>>findUserTodoGivenDate(@RequestParam Long userId, @RequestParam String date){
        List<TodoResponseDto> todoList = todoService.findUserTodoDaily(userId,date);
        return ResponseEntity.ok(todoList);
    }
    @GetMapping("/weekly")
    public ResponseEntity<List<TodoResponseDto>>findUserTodoWeekly(@RequestParam Long userId){
        List<TodoResponseDto> todoList = todoService.findUserTodoWeekly(userId);
        return ResponseEntity.ok(todoList);
    }
    @GetMapping("/monthly")
    public ResponseEntity<List<TodoResponseDto>>findUserTodoMonthly(@RequestParam Long userId){
        List<TodoResponseDto> todoList = todoService.findUserTodoMonthly(userId);
        return ResponseEntity.ok(todoList);
    }
    @PutMapping("/updateCompleteStatus")
    public ResponseEntity<TodoResponseDto>updateTodoStatus(@RequestParam Long userId, @RequestParam int index){
        TodoResponseDto todoResponseDto = todoService.updateTodoStatus(userId,index);
        return ResponseEntity.ok(todoResponseDto);
    }















}
