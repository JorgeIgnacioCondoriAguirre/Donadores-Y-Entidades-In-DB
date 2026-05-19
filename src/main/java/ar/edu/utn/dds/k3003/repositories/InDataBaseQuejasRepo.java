package ar.edu.utn.dds.k3003.repositories;


import ar.edu.utn.dds.k3003.model.Donador;
import ar.edu.utn.dds.k3003.model.NecesidadMaterial;
import ar.edu.utn.dds.k3003.model.Queja;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class InDataBaseQuejasRepo implements QuejasRepository{

    private EntityManager entityManager;
    private EntityTransaction transaction;

    public InDataBaseQuejasRepo(EntityManager entityManager, EntityTransaction transaction) {
        this.entityManager = entityManager;
        this.transaction = transaction;
    }

    @Override
    public Queja save(Queja queja) {
        if (queja.getId() == null || this.findById(queja.getId()).isEmpty()) {
            queja.setId(java.util.UUID.randomUUID().toString());
            entityManager.persist(queja);
            return queja;
        } else {
            return entityManager.merge(queja);
        }
}

    @Override
    public Optional<Queja> findById(String id) {
        if (id == null) {
            return Optional.empty();
        }
        Queja queja = entityManager.find(Queja.class, id);
        return Optional.ofNullable(queja);
    }

    @Override
    public List<Queja> quejasDeUnDonador(String donadorId) {
        String jpql = "SELECT n FROM Queja n WHERE n.donadorID = :donadorID";

        return entityManager.createQuery(jpql, Queja.class)
                .setParameter("donadorID", donadorId)
                .getResultList();
    }

}
