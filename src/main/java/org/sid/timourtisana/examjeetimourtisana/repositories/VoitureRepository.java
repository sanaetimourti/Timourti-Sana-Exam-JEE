package org.sid.timourtisana.examjeetimourtisana.repositories;
import org.sid.timourtisana.examjeetimourtisana.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long> {
    List<Voiture> findByTypeCarburant(TypeCarburant typeCarburant);
    List<Voiture> findByNombrePortes(Integer nombrePortes);
}
