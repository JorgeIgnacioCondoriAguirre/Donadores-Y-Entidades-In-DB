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

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
    EntityManager em = emf.createEntityManager();

    System.out.println("¡Conectado a PostgreSQL exitosamente!");

    // 2. Instanciamos tu Fachada (asegúrate de que tu Fachada tenga un constructor que reciba el EM o los repos de Postgres)
    Fachada fachada = new Fachada();

    // Asumiendo que agregaste setters o un constructor en tu Fachada para inyectar los nuevos repositorios:
//    fachada.setDonadoresRepository(new InDataBaseDonadoresRepo(em));
//    fachada.setQuejasRepository(new InDataBaseQuejasRepo(em));
//    fachada.setNecesidadMaterialRepository(new InDataBaseMaterialRepo(em));
//    fachada.setEntidadesBeneficasRepository(new InDataBaseBeneficasRepo(em));

    // 3. ¡LA PRUEBA DE FUEGO! Insertamos un donador real
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
      // Siempre cerramos las conexiones al terminar
      em.close();
      emf.close();
    }
  }
}
