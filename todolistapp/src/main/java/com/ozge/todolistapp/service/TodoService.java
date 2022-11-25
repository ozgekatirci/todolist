package com.ozge.todolistapp.service;

import com.ozge.todolistapp.converter.TodoConverter;
import com.ozge.todolistapp.converter.UserConverter;
import com.ozge.todolistapp.dto.RequestDto.TodoRequestDto;
import com.ozge.todolistapp.dto.ResponseDto.TodoResponseDto;
import com.ozge.todolistapp.dto.ResponseDto.UserResponseDto;
import com.ozge.todolistapp.entity.Todo;
import com.ozge.todolistapp.entity.User;
import com.ozge.todolistapp.repository.TodoRepository;
import com.ozge.todolistapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoConverter todoConverter;
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserResponseDto saveTodoByUserId(Long userId, TodoRequestDto todoRequestDto){
        Todo todo = todoConverter.convertTodoRequestDtoToTodo(todoRequestDto);
        User user = userService.findUserById(userId);
        user.getToDoList().add(todo);
        todoRepository.save(todo);
        userRepository.save(user);
        return userConverter.convertUserToUserToUserResponseDto(user);
    }

    public UserResponseDto removeTodoFromUser(Long userId, int index){
        User user = userService.findUserById(userId);
        Todo todo=findUserTodoByIndex(userId,index);
        user.getToDoList().remove(todo);
        todoRepository.deleteById(todo.getId());
        return userConverter.convertUserToUserToUserResponseDto(user);
    }

    public List<TodoResponseDto> findUserAllTodo(Long userId){
        User user = userService.findUserById(userId);
        List<TodoResponseDto> todoList = user.getToDoList().stream().
                map(todo->todoConverter.convertTodoToTodoResponseDto(todo))
                .collect(Collectors.toList());
        return todoList;
    }


    public TodoResponseDto updateTodoStatus(Long userId,int index){
        Todo todo =findUserTodoByIndex(userId,index);
        todo.setIsCompleted(!todo.getIsCompleted());
        todoRepository.save(todo);
        return todoConverter.convertTodoToTodoResponseDto(todo);
    }

    public List<TodoResponseDto> findUserTodoDaily(Long userId, String date){
        User user = userService.findUserById(userId);
        List<TodoResponseDto> todoList = user.getToDoList().stream().
                filter(todo->todo.getTaskDate().equals(date))
                .map(todo->todoConverter.convertTodoToTodoResponseDto(todo))
                .collect(Collectors.toList());
        return todoList;

    }
    public List<TodoResponseDto> findUserTodoWeekly(Long userId) {
        User user = userService.findUserById(userId);
        List<TodoResponseDto> todoList = user.getToDoList().stream().
                filter(todo -> todo.getTaskDate().isAfter(LocalDate.now().minusDays(7)))
                .map(todo -> todoConverter.convertTodoToTodoResponseDto(todo))
                .collect(Collectors.toList());
        return todoList;
    }
    public List<TodoResponseDto> findUserTodoMonthly(Long userId){
        User user = userService.findUserById(userId);
        List<TodoResponseDto> todoList = user.getToDoList().stream().
                filter(todo -> todo.getTaskDate().isAfter(LocalDate.now().minusDays(30)))
                .map(todo -> todoConverter.convertTodoToTodoResponseDto(todo))
                .collect(Collectors.toList());
        return todoList;
    }

    public Todo findUserTodoByIndex(Long userId,int index){
        User user = userService.findUserById(userId);
        Todo todo = user.getToDoList().get(index);
        return todo;
    }

}
