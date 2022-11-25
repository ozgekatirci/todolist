package com.ozge.todolistapp.dto.ResponseDto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@Data
public class TodoResponseDto {
    private Long id;
    private String title;
    private String content;
    private Boolean isCompleted;
    private LocalDate taskDate;
}
