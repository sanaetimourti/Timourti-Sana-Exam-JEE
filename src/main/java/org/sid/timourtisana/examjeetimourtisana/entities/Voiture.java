package org.sid.timourtisana.examjeetimourtisana.entities;
import javax.persistence.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.sid.timourtisana.examjeetimourtisana.enums.TypeCarburant;
import org.sid.timourtisana.examjeetimourtisana.enums.BoiteVitesse;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Voiture extends Vehicule {
    private Integer nombrePortes;

    @Enumerated(EnumType.STRING)
    private TypeCarburant typeCarburant;

    @Enumerated(EnumType.STRING)
    private BoiteVitesse boiteVitesse;
}
