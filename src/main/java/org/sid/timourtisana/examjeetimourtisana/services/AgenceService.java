package org.sid.timourtisana.examjeetimourtisana.services;
import org.sid.timourtisana.examjeetimourtisana.dto.*;
import java.util.List;
public interface AgenceService {
    // CRUD
    AgenceDTO createAgence(AgenceDTO agenceDTO);
    AgenceDTO updateAgence(Long id, AgenceDTO agenceDTO);
    void deleteAgence(Long id);
    AgenceDTO getAgenceById(Long id);
    List<AgenceDTO> getAllAgences();

    // Recherche
    List<AgenceDTO> getAgencesByVille(String ville);
    List<AgenceDTO> searchAgences(String keyword);

    // Statistiques
    StatistiqueDTO getStatistiquesAgence(Long agenceId);
    Long getNombreVehiculesDisponibles(Long agenceId);

    // Vérifications
    boolean existsByNom(String nom);
    boolean hasVehiculesDisponibles(Long agenceId);
}
