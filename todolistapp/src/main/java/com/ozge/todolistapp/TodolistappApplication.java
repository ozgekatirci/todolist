
package com.ozge.todolistapp;

import com.ozge.todolistapp.entity.Todo;
import com.ozge.todolistapp.entity.User;
import com.ozge.todolistapp.repository.TodoRepository;
import com.ozge.todolistapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Locale;

@SpringBootApplication
public class TodolistappApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final TodoRepository todoRepository;

	public TodolistappApplication(UserRepository userRepository, TodoRepository toDoRepository) {
		this.userRepository = userRepository;
		this.todoRepository = toDoRepository;
	}



	public static void main(String[] args) {
		SpringApplication.run(TodolistappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String date1 ="28-Oct-2022";
		String date2 = "04-Dec-2022";

		DateTimeFormatter df = new DateTimeFormatterBuilder()
				// case insensitive to parse JAN and FEB
				.parseCaseInsensitive()
				// add pattern
				.appendPattern("dd-MMM-yyyy")
				// create formatter (use English Locale to parse month names)
				.toFormatter(Locale.ENGLISH);
		LocalDate  d1 = LocalDate.parse(date1, df);
		LocalDate  weekly_date = LocalDate.parse(date2, df);

		User user = User.builder()
				.Name("ozge")
				.Surname("katirci")
				.toDoList(new ArrayList<>())
				.build();

		User user1 = User.builder()
				.Name("melisa")
				.Surname("sarı")
				.toDoList(new ArrayList<>())
				.build();
		Todo todo= Todo.builder()
				.title("okul")
				.content("sabah 9'da derse gidilecek.")
				.taskDate(weekly_date)
				.isCompleted(false)
				.build();
		Todo todo1= Todo.builder()
				.title("aile")
				.content("akşam yemeği 7'de dışarda yenilecek.")
				.taskDate(d1)
				.isCompleted(false)
				.build();

		Todo todo2= Todo.builder()
				.title("aile")
				.content("alışverişe gidilecek.")
				.taskDate(d1)
				.isCompleted(true)
				.build();

		Todo todo3= Todo.builder()
				.title("gezi")
				.content("geziye gidilecek.")
				.taskDate(weekly_date)
				.isCompleted(false)
				.build();

		Todo todo4= Todo.builder()
				.title("hazırlık")
				.content("gezi için saat 5te bavul hazırlanacak.")
				.taskDate(weekly_date)
				.isCompleted(true)
				.build();



		user.getToDoList().add(todo);
		user.getToDoList().add(todo4);
		user1.getToDoList().add(todo1);
		user1.getToDoList().add(todo2);
		user1.getToDoList().add(todo3);
		todoRepository.save(todo);
		todoRepository.save(todo1);
		todoRepository.save(todo2);
		todoRepository.save(todo3);
		todoRepository.save(todo4);
		userRepository.save(user);
		userRepository.save(user1);




	}
}

