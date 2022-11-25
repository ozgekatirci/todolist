package com.ozge.todolistapp.entity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String Name;
    private String Surname;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Todo> toDoList;

}
