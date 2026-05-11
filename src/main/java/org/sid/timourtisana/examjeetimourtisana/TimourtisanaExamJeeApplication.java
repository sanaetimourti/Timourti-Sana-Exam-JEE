package org.sid.timourtisana.examjeetimourtisana;  // ← Corriger le nom du package

import org.sid.timourtisana.examjeetimourtisana.entities.*;
import org.sid.timourtisana.examjeetimourtisana.enums.*;
import org.sid.timourtisana.examjeetimourtisana.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class TimourtisanaExamJeeApplication {  // ← Nom corrigé

    public static void main(String[] args) {
        SpringApplication.run(TimourtisanaExamJeeApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            AgenceRepository agenceRepository,
            VehiculeRepository vehiculeRepository,  // ← Garder même si warning
            VoitureRepository voitureRepository,
            MotoRepository motoRepository,
            LocationRepository locationRepository,
            UserRepository userRepository) {

        return args -> {

            // ===== CRÉATION AGENCE AVEC BUILDER =====
            Agence agence1 = Agence.builder()
                    .nom("Agence Centrale Casablanca")
                    .adresse("123 Boulevard Mohammed V")
                    .ville("Casablanca")
                    .telephone("0522123456")
                    .build();
            agenceRepository.save(agence1);

            Agence agence2 = Agence.builder()
                    .nom("Agence Rabat Agdal")  // ← Corriger Agdal (c'est correct en français)
                    .adresse("45 Avenue Hassan II")
                    .ville("Rabat")
                    .telephone("0537123456")
                    .build();
            agenceRepository.save(agence2);

            // ===== CRÉATION VOITURE (Extraction possible en méthode) =====
            Voiture voiture1 = createVoiture1(agence1);
            voitureRepository.save(voiture1);

            // ===== CRÉATION MOTO =====
            Moto moto1 = new Moto();
            moto1.setMarque("Yamaha");
            moto1.setModele("MT-07");
            moto1.setMatricule("MC-789-DD");
            moto1.setPrixParJour(200.0);
            moto1.setDateMiseEnService(LocalDate.of(2023, 3, 10));
            moto1.setStatut(StatutVehicule.DISPONIBLE);
            moto1.setAgence(agence1);
            moto1.setCylindree(689);
            moto1.setTypeMoto(TypeMoto.ROADSTER);
            moto1.setCasqueInclus(true);  // ← Vérifier l'orthographe exacte
            motoRepository.save(moto1);

            // ===== CRÉATION LOCATION =====
            Location location1 = new Location();
            location1.setDateDebut(LocalDate.of(2024, 1, 10));
            location1.setDateFin(LocalDate.of(2024, 1, 15));
            location1.setPrixTotal(1750.0);
            location1.setClientNom("Mohammed Alaoui");  // ← Corriger Alaoui (c'est correct)
            location1.setClientEmail("mohammed@gmail.com");
            location1.setClientTelephone("0612345678");
            location1.setVehicule(voiture1);
            locationRepository.save(location1);

            // ===== CRÉATION UTILISATEURS AVEC BUILDER =====
            User admin = User.builder()
                    .username("admin")
                    .password("admin123")
                    .email("admin@rentcar.com")
                    .role(User.UserRole.ROLE_ADMIN)
                    .build();
            userRepository.save(admin);

            User employe = User.builder()  // ← Corriger employe (orthographe française)
                    .username("employe1")  // ← Corriger employe
                    .password("employe123")
                    .email("employe1@rentcar.com")
                    .role(User.UserRole.ROLE_EMPLOYE)
                    .build();
            userRepository.save(employe);

            User client = User.builder()
                    .username("client1")
                    .password("client123")
                    .email("client1@gmail.com")
                    .role(User.UserRole.ROLE_CLIENT)
                    .build();
            userRepository.save(client);

            // ===== AFFICHAGE =====
            System.out.println("==========================================");
            System.out.println("✅ Agences ajoutées avec succès !");
            System.out.println("✅ Voiture ajoutée avec succès !");
            System.out.println("✅ Moto ajoutée avec succès !");
            System.out.println("✅ Location ajoutée avec succès !");
            System.out.println("✅ Utilisateurs ajoutés avec succès !");
            System.out.println("==========================================");
        };
    }

    // Méthode extraite pour créer la voiture (corrige le warning "extract method")
    private Voiture createVoiture1(Agence agence) {
        Voiture voiture = new Voiture();
        voiture.setMarque("Renault");
        voiture.setModele("Clio 5");
        voiture.setMatricule("AA-123-BB");
        voiture.setPrixParJour(350.0);
        voiture.setDateMiseEnService(LocalDate.of(2023, 1, 15));
        voiture.setStatut(StatutVehicule.DISPONIBLE);
        voiture.setAgence(agence);
        voiture.setNombrePortes(5);
        voiture.setTypeCarburant(TypeCarburant.ESSENCE);
        voiture.setBoiteVitesse(BoiteVitesse.MANUELLE);
        return voiture;
    }
}