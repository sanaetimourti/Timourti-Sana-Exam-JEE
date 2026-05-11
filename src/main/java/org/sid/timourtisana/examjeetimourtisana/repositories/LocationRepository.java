package org.sid.timourtisana.examjeetimourtisana.repositories;
import org.sid.timourtisana.examjeetimourtisana.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByVehiculeId(Long vehiculeId);
    List<Location> findByClientEmail(String clientEmail);

    @Query("SELECT l FROM Location l WHERE l.dateDebut BETWEEN :startDate AND :endDate")
    List<Location> findLocationsByDateRange(LocalDate startDate, LocalDate endDate);

    @Query("SELECT COUNT(l) FROM Location l WHERE l.vehicule.id = :vehiculeId AND l.dateFin >= :currentDate")
    Long countLocationsEnCours(Long vehiculeId, LocalDate currentDate);
}