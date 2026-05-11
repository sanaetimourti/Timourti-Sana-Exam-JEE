package org.sid.timourtisana.examjeetimourtisana.repositories;
import org.sid.timourtisana.examjeetimourtisana.entities.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

    @Repository
    public interface AgenceRepository extends JpaRepository<Agence, Long> {
        List<Agence> findByVille(String ville);
        List<Agence> findByNomContaining(String nom);
    }
