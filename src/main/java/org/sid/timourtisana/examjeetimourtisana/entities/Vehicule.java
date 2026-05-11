package org.sid.timourtisana.examjeetimourtisana.entities;

import jakarta.persistence.*;
import lombok.*;
import org.sid.timourtisana.examjeetimourtisana.enums.StatutVehicule;

import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data  // ← @Data génère tous les getters/setters pour les classes filles
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