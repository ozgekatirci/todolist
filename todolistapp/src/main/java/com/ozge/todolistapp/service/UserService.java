package com.ozge.todolistapp.service;

import com.ozge.todolistapp.converter.UserConverter;
import com.ozge.todolistapp.dto.RequestDto.UserRequestDto;
import com.ozge.todolistapp.dto.ResponseDto.UserResponseDto;
import com.ozge.todolistapp.entity.User;
import com.ozge.todolistapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;


    public UserResponseDto saveUser(UserRequestDto userRequestDto){
        User user=userConverter.convertUserRequestDtoToUser(userRequestDto);
        userRepository.save(user);
        return userConverter.convertUserToUserToUserResponseDto(user) ;
    }
    public User findUserById(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("User not found"));
        return user;
    }
    public List<UserResponseDto> listOfAllUsers(){
        return userRepository.findAll().stream()
                .map(user->userConverter.convertUserToUserToUserResponseDto(user))
                .collect(Collectors.toList());
    }
    public UserResponseDto findUserResponseById(Long userId){
        return userConverter.convertUserToUserToUserResponseDto(findUserById(userId));
    }

    public UserResponseDto deleteUserById(Long userId){
        UserResponseDto userResponseDto = findUserResponseById(userId);
        userRepository.deleteById(userId);
        return userResponseDto;
    }

    public UserResponseDto updateUserById(UserRequestDto userRequestDTO,Long userId){
        User updatedUser = findUserById(userId);
        updatedUser.setName(userRequestDTO.getName());
        updatedUser.setSurname(userRequestDTO.getSurname());
        return userConverter.convertUserToUserToUserResponseDto(updatedUser);
    }



}