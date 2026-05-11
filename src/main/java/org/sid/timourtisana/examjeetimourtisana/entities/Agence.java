package org.sid.timourtisana.examjeetimourtisana.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import javax.persistence.*;
import java.util.List;

public class Agence {
    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Agence {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nom;
        private String adresse;
        private String ville;
        private String telephone;

        @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Vehicule> vehicules;
    }
}
