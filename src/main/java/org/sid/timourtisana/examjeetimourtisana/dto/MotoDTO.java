package org.sid.timourtisana.examjeetimourtisana.dto;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = true)
public class MotoDTO extends VehiculeDTO {
    private Integer cylindree;
    private String typeMoto;
    private Boolean casqueInclus;
}
