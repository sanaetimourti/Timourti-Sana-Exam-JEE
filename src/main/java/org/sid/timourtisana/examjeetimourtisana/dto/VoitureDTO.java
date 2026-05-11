package org.sid.timourtisana.examjeetimourtisana.dto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class VoitureDTO extends VehiculeDTO {
    private Integer nombrePortes;
    private String typeCarburant;
    private String boiteVitesse;
}
