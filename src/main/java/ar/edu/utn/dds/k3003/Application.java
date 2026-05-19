package ar.edu.utn.dds.k3003;

import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.DonadorDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.EstadoDonadorEnum;
import ar.edu.utn.dds.k3003.repositories.*;
import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
    System.out.println("Iniciando conexión con Render...");

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    System.out.println("¡Conectado a PostgreSQL exitosamente!");

    Fachada fachada = new Fachada(entityManager);

    try {
      DonadorDTO donadorPrueba = new DonadorDTO(
              "d-100", "Jorge", "Prueba", 24, "jorge@mail.com",
              "123456", "Calle 123", EstadoDonadorEnum.VERIFICADO, "Ocasional"
      );

      fachada.agregarDonador(donadorPrueba);
      System.out.println("¡Donador guardado en la nube de Render!");

    } catch (Exception e) {
      e.printStackTrace();
    } finally {

      entityManager.close();
      entityManagerFactory.close();
    }
  }
}
