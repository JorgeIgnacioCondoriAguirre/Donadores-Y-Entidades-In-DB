package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.model.EntidadBenefica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import lombok.val;
import java.util.List;
import java.util.Optional;

public class InDataBaseEntidadesBeneficasRepo implements EntidadesBeneficasRepository{

    private EntityManager entityManager;
    private EntityTransaction transaction;

    public InDataBaseEntidadesBeneficasRepo(EntityManager entityManager, EntityTransaction transaction) {
        this.entityManager = entityManager;
        this.transaction = transaction;
    }

    @Override
    public Optional<EntidadBenefica> findById(String id) {
        if (id == null) {
            return Optional.empty();
        }
        EntidadBenefica entidadBenefica = entityManager.find(EntidadBenefica.class, id);
        return Optional.ofNullable(entidadBenefica);
    }

    @Override
    @Transactional
    public EntidadBenefica save(EntidadBenefica entidadBenefica) {
        if (entidadBenefica.getId() == null || this.findById(entidadBenefica.getId()).isEmpty()) {
            entidadBenefica.setId(java.util.UUID.randomUUID().toString());
            entityManager.persist(entidadBenefica);
            return entidadBenefica;
        } else {
            return entityManager.merge(entidadBenefica);
        }
    }

    @Override
    public EntidadBenefica deleteById(String id) {
        val entidadOptional = this.findById(id);
        if (entidadOptional.isPresent()) {
            EntidadBenefica entidadBenefica = entidadOptional.get();
            entityManager.remove(entidadBenefica);
            return entidadBenefica;
        }
        return null;
    }

    @Override
    public List<EntidadBenefica> todasLasEntidades(){
        return entityManager.createQuery("SELECT e FROM EntidadBenefica e", EntidadBenefica.class).getResultList();
    }
}
