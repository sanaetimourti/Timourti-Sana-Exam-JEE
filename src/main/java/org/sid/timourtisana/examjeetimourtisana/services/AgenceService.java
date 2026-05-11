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
    // Vérifications
    boolean existsByNom(String nom);
    boolean hasVehiculesDisponibles(Long agenceId);
}
