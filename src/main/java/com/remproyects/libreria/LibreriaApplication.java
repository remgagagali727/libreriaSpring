package com.remproyects.libreria;

import com.remproyects.libreria.main.Main;
import com.remproyects.libreria.models.libro.Libro;
import com.remproyects.libreria.repository.AutorRepository;
import com.remproyects.libreria.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibreriaApplication implements CommandLineRunner {

	@Autowired
	LibroRepository libroRepository;
	@Autowired
	AutorRepository autorRepository;


	public static void main(String[] args) {
		SpringApplication.run(LibreriaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		new Main(libroRepository, autorRepository);
	}
}
