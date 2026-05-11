package org.sid.timourtisana.examjeetimourtisana.dto;
import lombok.Data;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;

@Data
public class AgenceDTO {
    private Long id;

    private String nom;

    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;

    @NotBlank(message = "La ville est obligatoire")
    private String ville;

    @Pattern(regexp = "^[0-9]{10}$", message = "Le numéro de téléphone doit contenir 10 chiffres")
    private String telephone;
}
