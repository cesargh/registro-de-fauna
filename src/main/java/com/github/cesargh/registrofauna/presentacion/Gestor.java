package com.github.cesargh.registrofauna.presentacion;

import com.github.cesargh.registrofauna.modelo.Animal;
import com.github.cesargh.registrofauna.modelo.Pais;
import com.github.cesargh.registrofauna.modelo.TipoAnimal;
import com.github.cesargh.registrofauna.repositorio.AnimalRepository;
import com.github.cesargh.registrofauna.repositorio.PaisRepository;
import jakarta.persistence.EntityManager;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.dao.DataIntegrityViolationException;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Gestor {

    //region [Category: Variables}

    private final EntityManager entityManager;
    private final AnimalRepository animalRepository;
    private final PaisRepository paisRepository;
    private final Scanner scanner;
    private final Presentador presentador;

    //endregion [Category: Variables}

    //region [Category: Constructores}

    public Gestor(EntityManager entityManager, AnimalRepository animalRepository, PaisRepository paisRepository) {
        this.entityManager = entityManager;
        this.animalRepository = animalRepository;
        this.paisRepository = paisRepository;
        this.scanner = new Scanner(System.in);
        this.presentador = new Presentador();
    }

    //endregion [Category: Constructores}

    //region [Category: Métodos}

    private int IngresarAnimalAnio() {
        presentador.Imprimir("---------- Paso 3 de 5 ----------");
        presentador.Imprimir("Ingrese el año de avistamiento.");
        presentador.Imprimir("(entre 1900 y el año actual)");
        presentador.ImprimirPrompt("Por ejemplo: 2015");
        int valor = 0;
        String entrada = null;
        while (scanner.hasNext()) {
            entrada = scanner.next();
            try {
                try {
                    valor = Integer.valueOf(entrada);
                } catch (Exception e) {
                    throw new InvalidParameterException("El año debe ser un entero");
                }
                if ((valor < 1900) || (valor > LocalDate.now().getYear())) {
                    throw new InvalidParameterException("El año está fuera de rango");
                } else {
                    break;
                }
            } catch (Exception e) {
                presentador.ImprimirError("Ingreso inválido!");
                presentador.ImprimirError("(" + e.getMessage() + ")");
                presentador.ImprimirPrompt("Reintente");
            }
        }
        return valor;
    }

    private boolean IngresarAnimalExtincion() {
        presentador.Imprimir("---------- Paso 4 de 5 ----------");
        presentador.Imprimir("Ingrese si está extinto.");
        presentador.Imprimir("(S=Sí ó N=No)");
        presentador.ImprimirPrompt("Por ejemplo: N");
        boolean valor = false;
        String entrada = null;
        while (scanner.hasNext()) {
            entrada = scanner.next().toUpperCase().trim();
            try {
                if ((entrada.equals("S")) || (entrada.equals("N"))) {
                    valor = entrada.equals("S");
                    break;
                } else {
                    throw new InvalidParameterException("La respuesta deber ser S ó N");
                }
            } catch (Exception e) {
                presentador.ImprimirError("Ingreso inválido!");
                presentador.ImprimirError("(" + e.getMessage() + ")");
                presentador.ImprimirPrompt("Reintente");
            }
        }
        return valor;
    }

    private String IngresarAnimalNombre() {
        presentador.Imprimir("---------- Paso 1 de 5 ----------");
        presentador.Imprimir("Ingrese el nombre.");
        presentador.Imprimir("(hasta 20 caracteres)");
        presentador.ImprimirPrompt("Por ejemplo: Tigre");
        String valor = null;
        String entrada = null;
        while (scanner.hasNext()) {
            entrada = scanner.next().toUpperCase().trim();
            try {
                if (entrada.isEmpty()) {
                    throw new InvalidParameterException("El nombre no debe omitirse");
                }
                else if (entrada.length() > 20) {
                    throw new InvalidParameterException("El nombre excede 20 caracteres");
                }
                else if (animalRepository.findByNombreIgnoreCase(entrada).isPresent()) {
                    throw new InvalidParameterException("El nombre ya existe");
                }
                else {
                    valor = entrada;
                    break;
                }
            } catch (Exception e) {
                presentador.ImprimirError("Ingreso inválido!");
                presentador.ImprimirError("(" + e.getMessage() + ")");
                presentador.ImprimirPrompt("Reintente");
            }
        }
        return valor;
    }

    private Set<String> IngresarAnimalPaises() {
        presentador.Imprimir("---------- Paso 5 de 5 ----------");
        presentador.Imprimir("Ingrese países de avistamiento.");
        presentador.Imprimir("(separados por comas y");
        presentador.Imprimir("cada uno hasta 20 caracteres)");
        presentador.ImprimirPrompt("Por ejemplo: Chile, Perú", true);
        Set<String> valor = null;
        String entrada = null;
        while (scanner.hasNextLine()) {
            entrada = scanner.nextLine().trim();
            if (!entrada.isEmpty()) {
                try {
                    Set<String> nombres = Arrays.stream(entrada.toUpperCase().split("\\s*,\\s*")).distinct().collect(Collectors.toSet());
                    StringBuilder sb = new StringBuilder();
                    for (String nombre : nombres) {
                        if (nombre.length() > 20) {
                            sb.append(nombre).append("\n");
                        }
                    }
                    if (!sb.isEmpty()) {
                        if (sb.length() == 1) {
                            throw new InvalidParameterException("El siguiente nombre:\n" + sb + "excede 20 caracteres");
                        } else {
                            throw new InvalidParameterException("Los siguientes nombres:\n" + sb + "exceden 20 caracteres");
                        }
                    }
                    valor = new HashSet<String>();
                    for (String nombre : nombres) {
                        if (!nombre.isEmpty()) {
                            valor.add(nombre);
                        }
                    }
                    break;
                } catch (Exception e) {
                    presentador.ImprimirError("Ingreso inválido!");
                    presentador.ImprimirError("(" + e.getMessage() + ")");
                    presentador.ImprimirPrompt("Reintente");
                }
            }
        }
        return valor;
    }

    private TipoAnimal IngresarAnimalTipo() {
        presentador.Imprimir("---------- Paso 2 de 5 ----------");
        presentador.Imprimir("Ingrese el tipo de animal.");
        presentador.Imprimir("Las opciones son:");
        for (TipoAnimal tipo : TipoAnimal.values()) {
            presentador.Imprimir(tipo.toMenu());
        }
        presentador.ImprimirPrompt("Por ejemplo: 1");
        TipoAnimal valor = null;
        String entrada = null;
        while (scanner.hasNext()) {
            entrada = scanner.next();
            try {
                valor = TipoAnimal.fromInt(Integer.valueOf(entrada));
                break;
            } catch (Exception e) {
                presentador.ImprimirError("Ingreso inválido!");
                presentador.ImprimirError("Las opciones son:");
                for (TipoAnimal tipo : TipoAnimal.values()) {
                    presentador.ImprimirError(tipo.toMenu());
                }
                presentador.ImprimirPrompt("Reintente");
            }
        }
        return valor;
    }

    private boolean Finalizar(String opcion) {
        if (opcion.compareToIgnoreCase("F") == 0) {
            presentador.Imprimir();
            presentador.Imprimir("Adios, y...");
            presentador.ImprimirFrase("Sé la persona que tu perro cree que eres!");
            return true;
        } else {
            return false;
        }
    }

    private void VerMenu() {
        presentador.Imprimir();
        presentador.ImprimirTitulo("Menú de opciones");
        presentador.ImprimirMenu("R"," : Registrar animal");
        presentador.ImprimirMenu("T"," : Ver todos los animales");
        presentador.ImprimirMenu("E"," : Ver animales extintos");
        presentador.ImprimirMenu("C"," : Ver cantidad de animales por países");
        presentador.ImprimirMenu("F"," : Finalizar");
        presentador.ImprimirPrompt("Ingrese opción");
    }

    public void Ejecutar() {
        presentador.ImprimirTitulo("RGISTRO DE FAUNA");
        VerMenu();
        String opcion;
        while (scanner.hasNext()) {
            opcion = scanner.next();
            if (Finalizar(opcion)) {
                break;
            } else {
                try {
                    if (opcion.compareToIgnoreCase("R") == 0) {
                        RegistrarAnimal();
                    } else if (opcion.compareToIgnoreCase("T") == 0) {
                        VerAnimales();
                    } else if (opcion.compareToIgnoreCase("E") == 0) {
                        VerAnimalesExtintos();
                    } else if (opcion.compareToIgnoreCase("C") == 0) {
                        VerAnimalesPorPaises();
                    } else {
                        presentador.ImprimirError("Opción inválida. Reintente!");
                    }
                } catch (Exception e) {
                    presentador.ImprimirError(e);
                } finally {
                    VerMenu();
                }
            }
        }
    }

    private void RegistrarAnimal() {
        presentador.Imprimir();
        presentador.ImprimirTitulo("Registrar animal");

        String nombre = IngresarAnimalNombre();
        TipoAnimal tipo = IngresarAnimalTipo();
        int anio = IngresarAnimalAnio();
        boolean extincion = IngresarAnimalExtincion();
        Set<String> paisesNombres = IngresarAnimalPaises();

        presentador.Imprimir("Procesando...");

        Exception excep = null;
        Long animalId = null;
        SessionImplementor sessionImp = (SessionImplementor) entityManager.getDelegate();
        Transaction transaction = sessionImp.getTransaction();
        try {
            transaction.begin();
            Animal animal = new Animal(tipo, nombre, anio, extincion);
            for (String paisNombre : paisesNombres) {
                Optional<Pais> optPais = paisRepository.findByNombreIgnoreCase(paisNombre);
                Pais pais = optPais.orElseGet(() -> new Pais(paisNombre));
                animal.getPaises().add(pais);
                pais.getAnimales().add(animal);
            }
            entityManager.persist(animal);
            transaction.commit();
            animalId = animal.getId();
        } catch (Exception e) {
            excep = e;
            transaction.rollback();
        } finally {
            if (excep == null) {
                presentador.ImprimirResultado("----------- Resultado -----------");
                presentador.ImprimirResultado("Animal registrado (Id : " + animalId + ")");
                presentador.ImprimirResultado("---------------------------------");
            } else {
                String errMsg = excep.getMessage();
                if (excep instanceof DataIntegrityViolationException) {
                    if (errMsg.toLowerCase().contains("uniqueconstraintanimalnombre")) {
                        errMsg = "Error. Nombre duplicado: " + nombre;
                    }
                }
                presentador.ImprimirError("----------- Resultado -----------");
                presentador.ImprimirError(errMsg);
                presentador.ImprimirError("---------------------------------");
            }
        }
    }

    private void VerAnimales() {
        presentador.Imprimir();
        presentador.ImprimirTitulo("Ver todos los animales");
        presentador.Imprimir("Procesando...");
        try {
            var resultado = animalRepository.findAll();
            if (resultado.isEmpty()) {
                presentador.ImprimirInforme("No hay animales");
            } else {
                for (Animal e : resultado) {
                    presentador.ImprimirInforme(e.toString());
                }
                presentador.Imprimir("Fin del informe.");
            }
        } catch (Exception excep) {
            presentador.ImprimirError("Error leyendo datos.");
            presentador.ImprimirError(excep);
        }
    }

    private void VerAnimalesExtintos() {
        presentador.Imprimir();
        presentador.ImprimirTitulo("Ver animales extintos");
        presentador.Imprimir("Procesando...");
        try {
            var resultado = animalRepository.findByExtincion(true);
            if(resultado.isPresent() && !resultado.get().isEmpty()) {
                for (Animal e : resultado.get()) {
                    presentador.ImprimirInforme(e.toString());
                }
                presentador.Imprimir("Fin del informe.");
            } else {
                presentador.ImprimirInforme("No hay animales extintos");
            }
        } catch (Exception excep) {
            presentador.ImprimirError("Error leyendo datos.");
            presentador.ImprimirError(excep);
        }
    }

    private void VerAnimalesPorPaises() {
        presentador.Imprimir();
        presentador.ImprimirTitulo("Ver cantidad de animales por países");
        presentador.Imprimir("Procesando...");
        try {
            var resultado = paisRepository.findByNameAnimalesCount();
            if(resultado.isPresent() && !resultado.get().isEmpty()) {
                for (Object e : resultado.get()) {
                    presentador.ImprimirInforme(((Object[])e)[0] + " : " + ((Object[])e)[1]);
                }
                presentador.Imprimir("Fin del informe.");
            } else {
                presentador.ImprimirInforme("No hay datos");
            }
        } catch (Exception excep) {
            presentador.ImprimirError("Error leyendo datos.");
            presentador.ImprimirError(excep);
        }
    }

    //endregion [Category: Métodos}

}

//endregion [Category: Métodos}
