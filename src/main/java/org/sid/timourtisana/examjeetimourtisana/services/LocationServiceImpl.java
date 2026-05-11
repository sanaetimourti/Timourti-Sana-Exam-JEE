package org.sid.timourtisana.examjeetimourtisana.services;
import com.votre_nom.exam.dto.LocationDTO;
import com.votre_nom.exam.dto.StatistiqueDTO;
import com.votre_nom.exam.entities.Location;
import com.votre_nom.exam.entities.Vehicule;
import com.votre_nom.exam.enums.StatutVehicule;
import com.votre_nom.exam.mapper.LocationMapper;
import com.votre_nom.exam.repositories.LocationRepository;
import com.votre_nom.exam.repositories.VehiculeRepository;
import com.votre_nom.exam.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final VehiculeRepository vehiculeRepository;
    private final LocationMapper locationMapper;

    @Override
    public LocationDTO createLocation(LocationDTO locationDTO) {
        log.info("Création d'une nouvelle location pour le véhicule ID: {}",
                locationDTO.getVehiculeId());

        // Validation des dates
        if (locationDTO.getDateDebut().isAfter(locationDTO.getDateFin())) {
            throw new RuntimeException("La date de début doit être avant la date de fin");
        }

        if (locationDTO.getDateDebut().isBefore(LocalDate.now())) {
            throw new RuntimeException("La date de début ne peut pas être dans le passé");
        }

        // Vérifier la disponibilité du véhicule
        if (!isVehiculeDisponible(locationDTO.getVehiculeId(),
                locationDTO.getDateDebut(),
                locationDTO.getDateFin())) {
            throw new RuntimeException("Le véhicule n'est pas disponible pour cette période");
        }

        Vehicule vehicule = vehiculeRepository.findById(locationDTO.getVehiculeId())
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));

        // Calculer le prix
        long nombreJours = ChronoUnit.DAYS.between(locationDTO.getDateDebut(),
                locationDTO.getDateFin());
        double prixTotal = nombreJours * vehicule.getPrixParJour();

        // Créer la location
        Location location = locationMapper.toEntity(locationDTO);
        location.setVehicule(vehicule);
        location.setPrixTotal(prixTotal);
        location.setNombreJours(nombreJours);

        // Mettre à jour le statut du véhicule
        if (locationDTO.getDateDebut().isEqual(LocalDate.now())) {
            vehicule.setStatut(StatutVehicule.LOUE);
            vehiculeRepository.save(vehicule);
        }

        location = locationRepository.save(location);

        log.info("Location créée avec succès, ID: {}, Prix total: {}",
                location.getId(), prixTotal);

        return locationMapper.toDTO(location);
    }

    @Override
    @Transactional(readOnly = true)
    public LocationDTO getLocationById(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location non trouvée"));
        return locationMapper.toDTO(location);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(locationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LocationDTO terminerLocation(Long id) {
        log.info("Terminaison de la location ID: {}", id);

        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location non trouvée"));

        location.setDateFin(LocalDate.now());
        location = locationRepository.save(location);

        // Remettre le véhicule disponible
        Vehicule vehicule = location.getVehicule();
        vehicule.setStatut(StatutVehicule.DISPONIBLE);
        vehiculeRepository.save(vehicule);

        log.info("Location terminée avec succès");
        return locationMapper.toDTO(location);
    }

    @Override
    public LocationDTO annulerLocation(Long id) {
        log.info("Annulation de la location ID: {}", id);

        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location non trouvée"));

        // Ne peut annuler que si la location n'a pas commencé
        if (location.getDateDebut().isBefore(LocalDate.now()) ||
                location.getDateDebut().isEqual(LocalDate.now())) {
            throw new RuntimeException("Impossible d'annuler une location déjà commencée");
        }

        locationRepository.delete(location);

        log.info("Location annulée avec succès");
        return locationMapper.toDTO(location);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LocationDTO> getLocationsByVehicule(Long vehiculeId) {
        return locationRepository.findByVehiculeId(vehiculeId).stream()
                .map(locationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LocationDTO> getLocationsByClient(String email) {
        return locationRepository.findByClientEmail(email).stream()
                .map(locationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LocationDTO> getLocationsByDateRange(LocalDate debut, LocalDate fin) {
        return locationRepository.findLocationsByDateRange(debut, fin).stream()
                .map(locationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LocationDTO> getLocationsEnCours() {
        LocalDate today = LocalDate.now();
        return locationRepository.findAll().stream()
                .filter(l -> !l.getDateDebut().isAfter(today) &&
                        !l.getDateFin().isBefore(today))
                .map(locationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isVehiculeDisponible(Long vehiculeId, LocalDate dateDebut, LocalDate dateFin) {
        List<Location> locations = locationRepository.findByVehiculeId(vehiculeId);

        return locations.stream().noneMatch(location ->
                // Vérifier le chevauchement des dates
                !(dateFin.isBefore(location.getDateDebut()) ||
                        dateDebut.isAfter(location.getDateFin()))
        );
    }

    @Override
    public double calculerPrixLocation(Long vehiculeId, LocalDate dateDebut, LocalDate dateFin) {
        Vehicule vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));

        long nombreJours = ChronoUnit.DAYS.between(dateDebut, dateFin);
        double prixBase = nombreJours * vehicule.getPrixParJour();

        // Réduction pour location longue durée
        if (nombreJours >= 7) {
            prixBase *= 0.9; // 10% de réduction
        }
        if (nombreJours >= 30) {
            prixBase *= 0.85; // 15% de réduction
        }

        return prixBase;
    }

    @Override
    @Transactional(readOnly = true)
    public StatistiqueDTO getStatistiquesGlobales() {
        List<Location> allLocations = locationRepository.findAll();

        StatistiqueDTO stats = new StatistiqueDTO();
        stats.setTotalLocations((long) allLocations.size());

        // Chiffre d'affaires total
        double caTotal = allLocations.stream()
                .mapToDouble(Location::getPrixTotal)
                .sum();
        stats.setChiffreAffairesTotal(caTotal);

        // Prix moyen
        stats.setPrixMoyenLocation(allLocations.isEmpty() ? 0 :
                caTotal / allLocations.size());

        // Durée moyenne
        double dureeMoyenne = allLocations.stream()
                .mapToLong(l -> ChronoUnit.DAYS.between(l.getDateDebut(), l.getDateFin()))
                .average()
                .orElse(0);
        stats.setDureeMoyenneLocation((long) dureeMoyenne);

        // Statistiques véhicules
        List<Vehicule> allVehicules = vehiculeRepository.findAll();
        stats.setTotalVehicules((long) allVehicules.size());
        stats.setVehiculesDisponibles(allVehicules.stream()
                .filter(v -> v.getStatut() == StatutVehicule.DISPONIBLE).count());

        return stats;
    }

    @Override
    @Transactional(readOnly = true)
    public Double getChiffreAffairesMensuel(int mois, int annee) {
        LocalDate debut = LocalDate.of(annee, mois, 1);
        LocalDate fin = debut.plusMonths(1).minusDays(1);

        return locationRepository.findAll().stream()
                .filter(l -> !l.getDateDebut().isAfter(fin) &&
                        !l.getDateFin().isBefore(debut))
                .mapToDouble(Location::getPrixTotal)
                .sum();
    }
}