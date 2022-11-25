package com.ozge.todolistapp.dto.ResponseDto;

import com.ozge.todolistapp.entity.Todo;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;


@Getter
@Setter
@Builder
@Data
public class UserResponseDto {
    private Long id;
    private String Name;
    private String Surname;
    private List<Todo> toDoList;
}
