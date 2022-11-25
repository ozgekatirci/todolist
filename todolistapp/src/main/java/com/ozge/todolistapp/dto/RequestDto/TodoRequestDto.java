package com.ozge.todolistapp.dto.RequestDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Data
public class TodoRequestDto {
    private String title;
    private String content;
    private LocalDate taskDate;
    private Boolean isCompleted;
}
