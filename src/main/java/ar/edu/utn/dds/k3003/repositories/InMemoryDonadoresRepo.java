package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.model.Donador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;

public class InMemoryDonadoresRepo implements DonadoresRepository {
  EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db");
  EntityManager entityManager = entityManagerFactory .createEntityManager();

  @Override
  public Optional<Donador> findById(String id) {
    Donador donador = entityManager.find(Donador.class, id);
    return Optional.ofNullable(donador);
  }

  @Override
  public Donador save(Donador donador) {
    if (donador.getId() == null || this.findById(donador.getId()).isEmpty()) {
      entityManager.persist(donador);
      return donador;
    } else {
      return entityManager.merge(donador);
    }
  }

  @Override
  public Donador deleteById(String id) {
    var donadorOptional = this.findById(id);
    if (donadorOptional.isPresent()) {
      Donador donador = donadorOptional.get();
      entityManager.remove(donador);
      return donador;
    }
    return null;
  }

  @Override
  public List<Donador> todosLosDonadores() {
    return entityManager.createQuery("SELECT Donador FROM Donador donador", Donador.class).getResultList();
  }
}



