package com.ozge.todolistapp.controller;


import com.ozge.todolistapp.dto.RequestDto.UserRequestDto;
import com.ozge.todolistapp.dto.ResponseDto.UserResponseDto;
import com.ozge.todolistapp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = userService.saveUser(userRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/getById")
    public ResponseEntity<UserResponseDto> findUserById(@RequestParam Long userId){
        UserResponseDto userResponseDto = userService.findUserResponseById(userId);
        return ResponseEntity.ok(userResponseDto);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto,Long userId){
        UserResponseDto userResponseDto = userService.updateUserById(userRequestDto, userId);
        return ResponseEntity.ok(userResponseDto);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUserById(@RequestParam Long userId){
        userService.deleteUserById(userId);
        return ResponseEntity.ok("deleted");
    }


}
