package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.model.EntidadBenefica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.val;
import java.util.List;
import java.util.Optional;

public class InDataBaseEntidadesBeneficasRepo implements EntidadesBeneficasRepository{

    private EntityManager entityManager;
    public InDataBaseEntidadesBeneficasRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<EntidadBenefica> findById(String id) {
        EntidadBenefica entidadBenefica = entityManager.find(EntidadBenefica.class, id);
        return Optional.ofNullable(entidadBenefica);
    }

    @Override
    public EntidadBenefica save(EntidadBenefica entidadBenefica) {
        if (entidadBenefica.getId() == null || this.findById(entidadBenefica.getId()).isEmpty()) {
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
