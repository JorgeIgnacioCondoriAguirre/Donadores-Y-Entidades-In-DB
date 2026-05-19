package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.model.Donador;
import ar.edu.utn.dds.k3003.model.NecesidadMaterial;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;


public class InDataBaseNecesidadMaterialRepo implements NecesidadMaterialRepository{

    private EntityManager entityManager;
    private EntityTransaction transaction;

    public InDataBaseNecesidadMaterialRepo(EntityManager entityManager, EntityTransaction transaction) {
        this.entityManager = entityManager;
        this.transaction = transaction;
    }
    @Override
    public NecesidadMaterial save(NecesidadMaterial necesidadMaterial) {
        if (necesidadMaterial.getId() == null || this.findById(necesidadMaterial.getId()).isEmpty()) {
            necesidadMaterial.setId(java.util.UUID.randomUUID().toString());
            entityManager.persist(necesidadMaterial);
            return necesidadMaterial;
        } else {
            return entityManager.merge(necesidadMaterial);
        }
    }

    @Override
    public NecesidadMaterial deleteById(String id) {
        val necesidadOptional = this.findById(id);
        if (necesidadOptional.isPresent()) {
            NecesidadMaterial necesidadMaterial = necesidadOptional.get();
            entityManager.remove(necesidadOptional);
            return necesidadMaterial;
        }
        return null;
        }

    @Override
    public Optional<NecesidadMaterial> findById(String id){
        if (id == null) {
            return Optional.empty();
        }
        NecesidadMaterial necesidadMaterial = entityManager.find(NecesidadMaterial.class, id);
        return Optional.ofNullable(necesidadMaterial);
    }

    @Override
    public List<NecesidadMaterial> todasLasNecesidades(String productoSolicitadoID) {

        String jpql = "SELECT n FROM NecesidadMaterial n WHERE n.productoSolicitadoID = :productoId";

        return entityManager.createQuery(jpql, NecesidadMaterial.class)
                .setParameter("productoId", productoSolicitadoID)
                .getResultList();
    }
    public List<NecesidadMaterial> todasNecesidades(){
        return entityManager.createQuery("SELECT n FROM NecesidadMaterial n", NecesidadMaterial.class).getResultList();
    }
}
