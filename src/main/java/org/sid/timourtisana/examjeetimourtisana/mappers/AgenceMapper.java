package org.sid.timourtisana.examjeetimourtisana.mappers;
import org.sid.timourtisana.examjeetimourtisana.dto.AgenceDTO;
import org.sid.timourtisana.examjeetimourtisana.entities.Agence;
import org.sid.timourtisana.examjeetimourtisana.entities.Vehicule;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class AgenceMapper {

    public AgenceDTO toDTO(Agence agence) {
        if (agence == null) return null;

        AgenceDTO dto = new AgenceDTO();
        dto.setId(agence.getId());
        dto.setNom(agence.getNom());
        dto.setAdresse(agence.getAdresse());
        dto.setVille(agence.getVille());
        dto.setTelephone(agence.getTelephone());

        if (agence.getVehicules() != null) {
            dto.setNombreVehicules(agence.getVehicules().size());
            dto.setVehiculeIds(agence.getVehicules().stream()
                    .map(Vehicule::getId)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public Agence toEntity(AgenceDTO dto) {
        if (dto == null) return null;

        Agence agence = new Agence();
        agence.setId(dto.getId());
        agence.setNom(dto.getNom());
        agence.setAdresse(dto.getAdresse());
        agence.setVille(dto.getVille());
        agence.setTelephone(dto.getTelephone());

        return agence;
    }

    public void updateEntity(Agence agence, AgenceDTO dto) {
        agence.setNom(dto.getNom());
        agence.setAdresse(dto.getAdresse());
        agence.setVille(dto.getVille());
        agence.setTelephone(dto.getTelephone());
    }
}
