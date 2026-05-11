
package org.sid.timourtisana.examjeetimourtisana.entities;
import jakarta.persistence.*;
import lombok.*;
import org.sid.timourtisana.examjeetimourtisana.enums.TypeMoto;

    @Entity
    @Data  // ← @Data génère tous les getters/setters
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public class Moto extends Vehicule {
        private Integer cylindree;

        @Enumerated(EnumType.STRING)
        private TypeMoto typeMoto;

        private Boolean casqueInclus;  // ← Vérifier l'orthographe : casqueInclus (pas casquelnclus)
    }
