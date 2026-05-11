package org.sid.timourtisana.examjeetimourtisana.mappers;
import org.sid.timourtisana.examjeetimourtisana.dto.*;
import org.sid.timourtisana.examjeetimourtisana.entities.*;
import org.sid.timourtisana.examjeetimourtisana.enums.BoiteVitesse;
import org.sid.timourtisana.examjeetimourtisana.enums.StatutVehicule;
import org.sid.timourtisana.examjeetimourtisana.enums.TypeCarburant;
import org.sid.timourtisana.examjeetimourtisana.enums.TypeMoto;
import org.springframework.stereotype.Component;

@Component
public class VehiculeMapper {

    public VehiculeDTO toBaseDTO(Vehicule vehicule) {
        if (vehicule == null) return null;

        VehiculeDTO dto = new VehiculeDTO();
        dto.setId(vehicule.getId());
        dto.setMarque(vehicule.getMarque());
        dto.setModele(vehicule.getModele());
        dto.setMatricule(vehicule.getMatricule());
        dto.setPrixParJour(vehicule.getPrixParJour());
        dto.setDateMiseEnService(vehicule.getDateMiseEnService());
        dto.setStatut(vehicule.getStatut().name());

        if (vehicule.getAgence() != null) {
            dto.setAgenceId(vehicule.getAgence().getId());
            dto.setAgenceNom(vehicule.getAgence().getNom());
        }

        return dto;
    }

    public VoitureDTO toVoitureDTO(Voiture voiture) {
        if (voiture == null) return null;

        VoitureDTO dto = new VoitureDTO();
        fillBaseDTO(dto, voiture);
        dto.setType("VOITURE");
        dto.setNombrePortes(voiture.getNombrePortes());
        dto.setTypeCarburant(voiture.getTypeCarburant().name());
        dto.setBoiteVitesse(voiture.getBoiteVitesse().name());

        return dto;
    }

    public MotoDTO toMotoDTO(Moto moto) {
        if (moto == null) return null;

        MotoDTO dto = new MotoDTO();
        fillBaseDTO(dto, moto);
        dto.setType("MOTO");
        dto.setCylindree(moto.getCylindree());
        dto.setTypeMoto(moto.getTypeMoto().name());
        dto.setCasqueInclus(moto.getCasqueInclus());

        return dto;
    }

    private void fillBaseDTO(VehiculeDTO dto, Vehicule vehicule) {
        dto.setId(vehicule.getId());
        dto.setMarque(vehicule.getMarque());
        dto.setModele(vehicule.getModele());
        dto.setMatricule(vehicule.getMatricule());
        dto.setPrixParJour(vehicule.getPrixParJour());
        dto.setDateMiseEnService(vehicule.getDateMiseEnService());
        dto.setStatut(vehicule.getStatut().name());

        if (vehicule.getAgence() != null) {
            dto.setAgenceId(vehicule.getAgence().getId());
            dto.setAgenceNom(vehicule.getAgence().getNom());
        }
    }

    public Voiture toVoitureEntity(VoitureDTO dto) {
        if (dto == null) return null;

        Voiture voiture = new Voiture();
        fillBaseEntity(voiture, dto);
        voiture.setNombrePortes(dto.getNombrePortes());
        voiture.setTypeCarburant(TypeCarburant.valueOf(dto.getTypeCarburant()));
        voiture.setBoiteVitesse(BoiteVitesse.valueOf(dto.getBoiteVitesse()));

        return voiture;
    }

    public Moto toMotoEntity(MotoDTO dto) {
        if (dto == null) return null;

        Moto moto = new Moto();
        fillBaseEntity(moto, dto);
        moto.setCylindree(dto.getCylindree());
        moto.setTypeMoto(TypeMoto.valueOf(dto.getTypeMoto()));
        moto.setCasqueInclus(dto.getCasqueInclus());

        return moto;
    }

    private void fillBaseEntity(Vehicule vehicule, VehiculeDTO dto) {
        vehicule.setMarque(dto.getMarque());
        vehicule.setModele(dto.getModele());
        vehicule.setMatricule(dto.getMatricule());
        vehicule.setPrixParJour(dto.getPrixParJour());
        vehicule.setDateMiseEnService(dto.getDateMiseEnService());
        if (dto.getStatut() != null) {
            vehicule.setStatut(StatutVehicule.valueOf(dto.getStatut()));
        }
    }
}
