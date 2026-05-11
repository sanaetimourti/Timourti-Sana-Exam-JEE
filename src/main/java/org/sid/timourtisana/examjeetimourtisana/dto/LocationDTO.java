package org.sid.timourtisana.examjeetimourtisana.dto;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class LocationDTO {
    private Long id;

    @NotNull(message = "La date de début est obligatoire")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    private LocalDate dateFin;

    private Double prixTotal;

    @NotBlank(message = "Le nom du client est obligatoire")
    private String clientNom;

    @NotBlank(message = "L'email du client est obligatoire")
    private String clientEmail;

    private String clientTelephone;
    private Long vehiculeId;
}