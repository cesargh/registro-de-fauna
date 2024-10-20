package com.github.cesargh.registrofauna;

import com.github.cesargh.registrofauna.presentacion.Gestor;
import com.github.cesargh.registrofauna.repositorio.AnimalRepository;
import com.github.cesargh.registrofauna.repositorio.PaisRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class RegistroDeFaunaApplication implements CommandLineRunner {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private PaisRepository paisRepository;

	public static void main(String[] args) {
		SpringApplication.run(RegistroDeFaunaApplication.class, args);
	}

	@Transactional(propagation = Propagation.NEVER)
	@Override
	public void run(String... args) throws Exception {
		Gestor gestor = new Gestor(entityManager, animalRepository, paisRepository);
		gestor.Ejecutar();
	}

}
