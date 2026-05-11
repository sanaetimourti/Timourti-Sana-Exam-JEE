package org.sid.timourtisana.examjeetimourtisana.services;
import com.votre_nom.exam.dto.LocationDTO;
import com.votre_nom.exam.dto.StatistiqueDTO;
import java.time.LocalDate;
import java.util.List;

public interface LocationService {
    LocationDTO createLocation(LocationDTO locationDTO);
    LocationDTO getLocationById(Long id);
    List<LocationDTO> getAllLocations();

    // Gestion des locations
    LocationDTO terminerLocation(Long id);
    LocationDTO annulerLocation(Long id);

    // Recherche
    List<LocationDTO> getLocationsByVehicule(Long vehiculeId);
    List<LocationDTO> getLocationsByClient(String email);
    List<LocationDTO> getLocationsByDateRange(LocalDate debut, LocalDate fin);
    List<LocationDTO> getLocationsEnCours();

    // Vérifications
    boolean isVehiculeDisponible(Long vehiculeId, LocalDate dateDebut, LocalDate dateFin);
    double calculerPrixLocation(Long vehiculeId, LocalDate dateDebut, LocalDate dateFin);

    // Statistiques
    StatistiqueDTO getStatistiquesGlobales();
    Double getChiffreAffairesMensuel(int mois, int annee);
}
