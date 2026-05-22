package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.model.Donador;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.val;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

public class InDataBaseDonadoresRepo implements DonadoresRepository {

  private EntityManager entityManager;
  private EntityTransaction transaction;

  public InDataBaseDonadoresRepo(EntityManager entityManager, EntityTransaction transaction) {
    this.entityManager = entityManager;
    this.transaction = transaction;
  }

  @Override
  public Optional<Donador> findById(String id) {
    if (id == null) {
      return Optional.empty();
    }
    val donador = entityManager.find(Donador.class, id);
    return Optional.ofNullable(donador);
  }

  @Override
  @Transactional
  public Donador save(Donador donador) {
    if (donador.getId() == null) {
      entityManager.persist(donador);
      return donador;
    } else {
      return entityManager.merge(donador);
    }
  }

  @Override
  public Donador deleteById(String id) {
    val donadorOptional = this.findById(id);
    if (donadorOptional.isPresent()) {
      Donador donador = donadorOptional.get();
      entityManager.remove(donador);
      return donador;
    }
    return null;
  }

  @Override
  public List<Donador> todosLosDonadores() {
    return entityManager.createQuery("SELECT d FROM Donador d", Donador.class).getResultList();
  }
}



