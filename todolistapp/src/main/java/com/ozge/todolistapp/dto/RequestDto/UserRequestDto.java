package com.ozge.todolistapp.dto.RequestDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserRequestDto {
    private String name;
    private String surname;
}
