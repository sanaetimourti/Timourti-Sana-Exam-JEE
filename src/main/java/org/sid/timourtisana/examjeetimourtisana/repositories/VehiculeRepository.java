package org.sid.timourtisana.examjeetimourtisana.repositories;
import org.sid.timourtisana.examjeetimourtisana.entities.Vehicule;
import org.sid.timourtisana.examjeetimourtisana.enums.StatutVehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    List<Vehicule> findByAgenceId(Long agenceId);
    List<Vehicule> findByStatut(StatutVehicule statut);
    List<Vehicule> findByMarque(String marque);

    @Query("SELECT v FROM Vehicule v WHERE v.prixParJour BETWEEN :min AND :max")
    List<Vehicule> findByPrixParJourBetween(Double min, Double max);

    @Query("SELECT v FROM Vehicule v WHERE v.statut = 'DISPONIBLE' AND v.agence.id = :agenceId")
    List<Vehicule> findAvailableByAgence(Long agenceId);
}