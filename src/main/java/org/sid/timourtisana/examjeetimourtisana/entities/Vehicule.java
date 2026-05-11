package org.sid.timourtisana.examjeetimourtisana.entities;
import javax.persistence.*;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

    @Entity
    @Inheritance(strategy = InheritanceType.JOINED)
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public abstract class Vehicule {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String marque;
        private String modele;

        @Column(unique = true)
        private String matricule;

        private Double prixParJour;

        private LocalDate dateMiseEnService;

        @Enumerated(EnumType.STRING)
        private StatutVehicule statut;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "agence_id")
        private Agence agence;

        @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL)
        private List<Location> locations;
    }
}
