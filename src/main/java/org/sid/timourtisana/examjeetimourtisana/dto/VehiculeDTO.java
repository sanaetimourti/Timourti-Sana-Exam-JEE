package org.sid.timourtisana.examjeetimourtisana.dto;
import lombok.Data;

import java.time.LocalDate;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Positive;
//import java.time.LocalDate;

@Data
public class VehiculeDTO {
    private Long id;

    @NotBlank(message = "La marque est obligatoire")
    private String marque;

    @NotBlank(message = "Le modèle est obligatoire")
    private String modele;

    @NotBlank(message = "Le matricule est obligatoire")
    private String matricule;

    @NotNull(message = "Le prix par jour est obligatoire")
    @Positive(message = "Le prix doit être positif")
    private Double prixParJour;

    private LocalDate dateMiseEnService;
    private String statut;
    private Long agenceId;
    private String type;
}