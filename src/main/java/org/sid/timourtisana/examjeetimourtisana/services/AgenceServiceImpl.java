package org.sid.timourtisana.examjeetimourtisana.services;
// AgenceServiceImpl.java
//package com.votre_nom.exam.service.impl;
import org.sid.timourtisana.examjeetimourtisana.dto.AgenceDTO;
//import org.sid.timourtisana.examjeetimourtisana.dto.StatistiqueDTO;
 import org.sid.timourtisana.examjeetimourtisana.entities.Agence;
import org.sid.timourtisana.examjeetimourtisana.entities.Vehicule;
import org.sid.timourtisana.examjeetimourtisana.enums.StatutVehicule;
import org.sid.timourtisana.examjeetimourtisana.mappers.AgenceMapper;
import org.sid.timourtisana.examjeetimourtisana.repositories.AgenceRepository;
import org.sid.timourtisana.examjeetimourtisana.repositories.VehiculeRepository;
import org.sid.timourtisana.examjeetimourtisana.services.AgenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AgenceServiceImpl implements AgenceService {

    private final AgenceRepository agenceRepository;
    private final VehiculeRepository vehiculeRepository;
    private final AgenceMapper agenceMapper;

    @Override
    public AgenceDTO createAgence(AgenceDTO agenceDTO) {
        log.info("Création d'une nouvelle agence : {}", agenceDTO.getNom());

        if (existsByNom(agenceDTO.getNom())) {
            throw new RuntimeException("Une agence avec ce nom existe déjà");
        }

        Agence agence = agenceMapper.toEntity(agenceDTO);
        agence = agenceRepository.save(agence);

        log.info("Agence créée avec succès, ID: {}", agence.getId());
        return agenceMapper.toDTO(agence);
    }

    @Override
    public AgenceDTO updateAgence(Long id, AgenceDTO agenceDTO) {
        log.info("Mise à jour de l'agence ID: {}", id);

        Agence agence = agenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agence non trouvée avec l'ID: " + id));

        agenceMapper.updateEntity(agence, agenceDTO);
        agence = agenceRepository.save(agence);

        log.info("Agence mise à jour avec succès");
        return agenceMapper.toDTO(agence);
    }

    @Override
    public void deleteAgence(Long id) {
        log.info("Suppression de l'agence ID: {}", id);

        Agence agence = agenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agence non trouvée"));

        if (!agence.getVehicules().isEmpty()) {
            throw new RuntimeException("Impossible de supprimer une agence qui contient des véhicules");
        }

        agenceRepository.deleteById(id);
        log.info("Agence supprimée avec succès");
    }

    @Override
    @Transactional(readOnly = true)
    public AgenceDTO getAgenceById(Long id) {
        Agence agence = agenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agence non trouvée"));
        return agenceMapper.toDTO(agence);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AgenceDTO> getAllAgences() {
        return agenceRepository.findAll().stream()
                .map(agenceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AgenceDTO> getAgencesByVille(String ville) {
        return agenceRepository.findByVille(ville).stream()
                .map(agenceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AgenceDTO> searchAgences(String keyword) {
        String searchPattern = "%" + keyword.toLowerCase() + "%";
        return agenceRepository.findAll().stream()
                .filter(a -> a.getNom().toLowerCase().contains(keyword.toLowerCase()) ||
                        a.getVille().toLowerCase().contains(keyword.toLowerCase()))
                .map(agenceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public StatistiqueDTO getStatistiquesAgence(Long agenceId) {
        Agence agence = agenceRepository.findById(agenceId)
                .orElseThrow(() -> new RuntimeException("Agence non trouvée"));

        List<Vehicule> vehicules = agence.getVehicules();

        StatistiqueDTO stats = new StatistiqueDTO();
        stats.setTotalVehicules((long) vehicules.size());
        stats.setVehiculesDisponibles(vehicules.stream()
                .filter(v -> v.getStatut() == StatutVehicule.DISPONIBLE).count());
        stats.setVehiculesLoues(vehicules.stream()
                .filter(v -> v.getStatut() == StatutVehicule.LOUE).count());
        stats.setVehiculesEnMaintenance(vehicules.stream()
                .filter(v -> v.getStatut() == StatutVehicule.EN_MAINTENANCE).count());

        return stats;
    }

    @Override
    public Long getNombreVehiculesDisponibles(Long agenceId) {
        return vehiculeRepository.findAvailableByAgence(agenceId).stream().count();
    }

    @Override
    public boolean existsByNom(String nom) {
        return agenceRepository.findByNom(nom).isPresent();
    }

    @Override
    public boolean hasVehiculesDisponibles(Long agenceId) {
        return getNombreVehiculesDisponibles(agenceId) > 0;
    }
}