package org.sid.timourtisana.examjeetimourtisana;

import org.sid.timourtisana.examjeetimourtisana.entities.*;
import .enums.*;
import com.votre_nom.exam.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@SpringBootApplication
public class ExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
    }

    // ===== METHODE 1 : Test avec les repositories directement =====
    @Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            AgenceRepository agenceRepository,
                            VehiculeRepository vehiculeRepository,
                            VoitureRepository voitureRepository,
                            MotoRepository motoRepository,
                            LocationRepository locationRepository,
                            UserRepository userRepository) {
        return args -> {
            System.out.println("==========================================");
            System.out.println("🚀 DÉMARRAGE DES TESTS DE LA COUCHE DAO");
            System.out.println("==========================================");

            // ===== 1. Création des agences =====
            System.out.println("\n📁 Création des agences...");

            Stream.of("Casablanca Centre", "Rabat Agdal", "Marrakech Gueliz", "Tanger Ville")
                    .forEach(nom -> {
                        Agence agence = new Agence();
                        agence.setNom("Agence " + nom);
                        agence.setAdresse("123 Avenue " + nom.split(" ")[0]);
                        agence.setVille(nom.split(" ")[0]);
                        agence.setTelephone("05" + (new Random().nextInt(90000000) + 10000000));
                        agenceRepository.save(agence);
                        System.out.println("  ✅ Agence créée : " + agence.getNom() + " à " + agence.getVille());
                    });

            List<Agence> agences = agenceRepository.findAll();

            // ===== 2. Création des clients =====
            System.out.println("\n👥 Création des clients...");

            Stream.of("Mohammed Alaoui", "Fatima Zahra", "Youssef Benani", "Amina Chadli")
                    .forEach(name -> {
                        Customer customer = new Customer();
                        customer.setName(name);
                        customer.setEmail(name.toLowerCase().replace(" ", ".") + "@gmail.com");
                        customerRepository.save(customer);
                        System.out.println("  ✅ Client créé : " + customer.getName() + " - " + customer.getEmail());
                    });

            List<Customer> customers = customerRepository.findAll();

            // ===== 3. Création des voitures =====
            System.out.println("\n🚗 Création des voitures...");

            // Volkswagen Golf 8
            Voiture voiture1 = new Voiture();
            voiture1.setMarque("Volkswagen");
            voiture1.setModele("Golf 8");
            voiture1.setMatricule("AA-123-BB");
            voiture1.setPrixParJour(450.0);
            voiture1.setDateMiseEnService(LocalDate.of(2023, 1, 15));
            voiture1.setStatut(StatutVehicule.DISPONIBLE);
            voiture1.setAgence(agences.get(0));
            voiture1.setNombrePortes(5);
            voiture1.setTypeCarburant(TypeCarburant.ESSENCE);
            voiture1.setBoiteVitesse(BoiteVitesse.MANUELLE);
            voitureRepository.save(voiture1);
            System.out.println("  ✅ " + voiture1.getMarque() + " " + voiture1.getModele() + " créée");

            // Peugeot 3008
            Voiture voiture2 = new Voiture();
            voiture2.setMarque("Peugeot");
            voiture2.setModele("3008");
            voiture2.setMatricule("BB-456-CC");
            voiture2.setPrixParJour(550.0);
            voiture2.setDateMiseEnService(LocalDate.of(2023, 6, 1));
            voiture2.setStatut(StatutVehicule.DISPONIBLE);
            voiture2.setAgence(agences.get(1));
            voiture2.setNombrePortes(5);
            voiture2.setTypeCarburant(TypeCarburant.DIESEL);
            voiture2.setBoiteVitesse(BoiteVitesse.AUTOMATIQUE);
            voitureRepository.save(voiture2);
            System.out.println("  ✅ " + voiture2.getMarque() + " " + voiture2.getModele() + " créée");

            // Renault Clio
            Voiture voiture3 = new Voiture();
            voiture3.setMarque("Renault");
            voiture3.setModele("Clio 5");
            voiture3.setMatricule("CC-789-DD");
            voiture3.setPrixParJour(350.0);
            voiture3.setDateMiseEnService(LocalDate.of(2023, 3, 20));
            voiture3.setStatut(StatutVehicule.DISPONIBLE);
            voiture3.setAgence(agences.get(2));
            voiture3.setNombrePortes(5);
            voiture3.setTypeCarburant(TypeCarburant.HYBRIDE);
            voiture3.setBoiteVitesse(BoiteVitesse.AUTOMATIQUE);
            voitureRepository.save(voiture3);
            System.out.println("  ✅ " + voiture3.getMarque() + " " + voiture3.getModele() + " créée");

            // Dacia Duster
            Voiture voiture4 = new Voiture();
            voiture4.setMarque("Dacia");
            voiture4.setModele("Duster");
            voiture4.setMatricule("DD-012-EE");
            voiture4.setPrixParJour(400.0);
            voiture4.setDateMiseEnService(LocalDate.of(2023, 8, 10));
            voiture4.setStatut(StatutVehicule.DISPONIBLE);
            voiture4.setAgence(agences.get(3));
            voiture4.setNombrePortes(5);
            voiture4.setTypeCarburant(TypeCarburant.DIESEL);
            voiture4.setBoiteVitesse(BoiteVitesse.MANUELLE);
            voitureRepository.save(voiture4);
            System.out.println("  ✅ " + voiture4.getMarque() + " " + voiture4.getModele() + " créée");

            // ===== 4. Création des motos =====
            System.out.println("\n🏍️ Création des motos...");

            // Yamaha MT-07
            Moto moto1 = new Moto();
            moto1.setMarque("Yamaha");
            moto1.setModele("MT-07");
            moto1.setMatricule("MC-345-FF");
            moto1.setPrixParJour(250.0);
            moto1.setDateMiseEnService(LocalDate.of(2023, 4, 5));
            moto1.setStatut(StatutVehicule.DISPONIBLE);
            moto1.setAgence(agences.get(0));
            moto1.setCylindree(689);
            moto1.setTypeMoto(TypeMoto.ROADSTER);
            moto1.setCasqueInclus(true);
            motoRepository.save(moto1);
            System.out.println("  ✅ " + moto1.getMarque() + " " + moto1.getModele() + " créée");

            // Honda CBR600RR
            Moto moto2 = new Moto();
            moto2.setMarque("Honda");
            moto2.setModele("CBR600RR");
            moto2.setMatricule("MD-678-GG");
            moto2.setPrixParJour(300.0);
            moto2.setDateMiseEnService(LocalDate.of(2023, 7, 15));
            moto2.setStatut(StatutVehicule.DISPONIBLE);
            moto2.setAgence(agences.get(1));
            moto2.setCylindree(599);
            moto2.setTypeMoto(TypeMoto.SPORTIVE);
            moto2.setCasqueInclus(true);
            motoRepository.save(moto2);
            System.out.println("  ✅ " + moto2.getMarque() + " " + moto2.getModele() + " créée");

            // BMW R1250GS
            Moto moto3 = new Moto();
            moto3.setMarque("BMW");
            moto3.setModele("R1250GS");
            moto3.setMatricule("ME-901-HH");
            moto3.setPrixParJour(400.0);
            moto3.setDateMiseEnService(LocalDate.of(2023, 9, 1));
            moto3.setStatut(StatutVehicule.DISPONIBLE);
            moto3.setAgence(agences.get(2));
            moto3.setCylindree(1254);
            moto3.setTypeMoto(TypeMoto.TOURING);
            moto3.setCasqueInclus(true);
            motoRepository.save(moto3);
            System.out.println("  ✅ " + moto3.getMarque() + " " + moto3.getModele() + " créée");

            // ===== 5. Création des locations =====
            System.out.println("\n📋 Création des locations de test...");

            // Location 1 - Voiture
            Location location1 = new Location();
            location1.setDateDebut(LocalDate.now().minusDays(10));
            location1.setDateFin(LocalDate.now().minusDays(5));
            location1.setPrixTotal(2250.0);
            location1.setClientNom(customers.get(0).getName());
            location1.setClientEmail(customers.get(0).getEmail());
            location1.setClientTelephone("0612345678");
            location1.setVehicule(voiture1);
            location1.setStatutLocation("TERMINEE");
            locationRepository.save(location1);
            System.out.println("  ✅ Location créée pour " + customers.get(0).getName() + " - " + voiture1.getMarque());

            // Location 2 - Moto
            Location location2 = new Location();
            location2.setDateDebut(LocalDate.now().minusDays(3));
            location2.setDateFin(LocalDate.now().plusDays(2));
            location2.setPrixTotal(1250.0);
            location2.setClientNom(customers.get(1).getName());
            location2.setClientEmail(customers.get(1).getEmail());
            location2.setClientTelephone("0623456789");
            location2.setVehicule(moto1);
            location2.setStatutLocation("EN_COURS");
            locationRepository.save(location2);
            System.out.println("  ✅ Location créée pour " + customers.get(1).getName() + " - " + moto1.getMarque());

            // Location 3 - Voiture (future)
            Location location3 = new Location();
            location3.setDateDebut(LocalDate.now().plusDays(5));
            location3.setDateFin(LocalDate.now().plusDays(10));
            location3.setPrixTotal(2750.0);
            location3.setClientNom(customers.get(2).getName());
            location3.setClientEmail(customers.get(2).getEmail());
            location3.setClientTelephone("0634567890");
            location3.setVehicule(voiture2);
            location3.setStatutLocation("RESERVEE");
            locationRepository.save(location3);
            System.out.println("  ✅ Location créée pour " + customers.get(2).getName() + " - " + voiture2.getMarque());

            // Pour chaque véhicule, créer des locations multiples
            List<Vehicule> allVehicules = vehiculeRepository.findAll();
            allVehicules.forEach(vehicule -> {
                for (int i = 0; i < 3; i++) {
                    Location location = new Location();
                    location.setDateDebut(LocalDate.now().minusDays(30 + (i * 10)));
                    location.setDateFin(LocalDate.now().minusDays(25 + (i * 10)));
                    location.setPrixTotal(vehicule.getPrixParJour() * (5 + i));
                    location.setClientNom(customers.get(i % customers.size()).getName());
                    location.setClientEmail(customers.get(i % customers.size()).getEmail());
                    location.setClientTelephone("06000000" + (i + 1));
                    location.setVehicule(vehicule);
                    location.setStatutLocation("TERMINEE");
                    locationRepository.save(location);
                }
            });
            System.out.println("  ✅ " + (allVehicules.size() * 3) + " locations historiques créées");

            // ===== 6. Création des utilisateurs =====
            System.out.println("\n👤 Création des utilisateurs...");

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("$2a$10$encodedPassword"); // À encoder avec BCrypt
            admin.setEmail("admin@rentcar.com");
            admin.setRole(User.UserRole.ROLE_ADMIN);
            userRepository.save(admin);
            System.out.println("  ✅ Admin créé : admin");

            User employe = new User();
            employe.setUsername("employe1");
            employe.setPassword("$2a$10$encodedPassword");
            employe.setEmail("employe1@rentcar.com");
            employe.setRole(User.UserRole.ROLE_EMPLOYE);
            userRepository.save(employe);
            System.out.println("  ✅ Employé créé : employe1");

            User client = new User();
            client.setUsername("client1");
            client.setPassword("$2a$10$encodedPassword");
            client.setEmail("client1@gmail.com");
            client.setRole(User.UserRole.ROLE_CLIENT);
            userRepository.save(client);
            System.out.println("  ✅ Client créé : client1");

            // ===== 7. VÉRIFICATIONS ET AFFICHAGE DES RÉSULTATS =====
            System.out.println("\n==========================================");
            System.out.println("📊 RÉSULTATS DES TESTS");
            System.out.println("==========================================");

            // Compter les entités créées
            long nbAgences = agenceRepository.count();
            long nbVehicules = vehiculeRepository.count();
            long nbVoitures = voitureRepository.count();
            long nbMotos = motoRepository.count();
            long nbLocations = locationRepository.count();
            long nbCustomers = customerRepository.count();
            long nbUsers = userRepository.count();

            System.out.println("✅ Agences créées : " + nbAgences);
            System.out.println("✅ Clients créés : " + nbCustomers);
            System.out.println("✅ Véhicules créés : " + nbVehicules);
            System.out.println("   - Voitures : " + nbVoitures);
            System.out.println("   - Motos : " + nbMotos);
            System.out.println("✅ Locations créées : " + nbLocations);
            System.out.println("✅ Utilisateurs créés : " + nbUsers);

            // Afficher les agences avec leurs véhicules
            System.out.println("\n📋 DÉTAIL DES AGENCES :");
            agenceRepository.findAll().forEach(agence -> {
                System.out.println("\n🏢 " + agence.getNom() + " - " + agence.getVille());
                System.out.println("   📍 " + agence.getAdresse());
                System.out.println("   📞 " + agence.getTelephone());
                System.out.println("   🚗 Véhicules :");
                agence.getVehicules().forEach(v -> {
                    String type = v instanceof Voiture ? "Voiture" : "Moto";
                    System.out.println("      - " + type + " : " + v.getMarque() + " " + v.getModele() +
                            " | Matricule: " + v.getMatricule() +
                            " | Prix: " + v.getPrixParJour() + "€/jour" +
                            " | Statut: " + v.getStatut());
                });
            });

            // Afficher les véhicules disponibles
            System.out.println("\n🟢 VÉHICULES DISPONIBLES :");
            vehiculeRepository.findByStatut(StatutVehicule.DISPONIBLE).forEach(v -> {
                System.out.println("   ✅ " + v.getMarque() + " " + v.getModele() +
                        " - " + v.getPrixParJour() + "€/jour" +
                        " (Agence: " + v.getAgence().getNom() + ")");
            });

            // Afficher les locations en cours
            System.out.println("\n📅 LOCATIONS EN COURS :");
            locationRepository.findAll().stream()
                    .filter(l -> l.getStatutLocation().equals("EN_COURS"))
                    .forEach(l -> {
                        System.out.println("   🔄 " + l.getClientNom() +
                                " - " + l.getVehicule().getMarque() + " " + l.getVehicule().getModele() +
                                " | Du " + l.getDateDebut() + " au " + l.getDateFin() +
                                " | " + l.getPrixTotal() + "€");
                    });

            System.out.println("\n==========================================");
            System.out.println("✅ TESTS DE LA COUCHE DAO TERMINÉS AVEC SUCCÈS !");
            System.out.println("==========================================");
        };
    }

    // ===== METHODE 2 : Test avec les services (alternative commentée) =====
    // @Bean
    CommandLineRunner testWithServices(AgenceService agenceService,
                                       VehiculeService vehiculeService,
                                       LocationService locationService,
                                       UserService userService) {
        return args -> {
            System.out.println("🚀 Test avec les services...");

            // Test création agence via service
            Stream.of("Casablanca Centre", "Rabat Agdal", "Marrakech Gueliz")
                    .forEach(nom -> {
                        AgenceDTO agenceDTO = new AgenceDTO();
                        agenceDTO.setNom("Agence " + nom);
                        agenceDTO.setAdresse("123 Avenue " + nom.split(" ")[0]);
                        agenceDTO.setVille(nom.split(" ")[0]);
                        agenceDTO.setTelephone("05" + (new Random().nextInt(90000000) + 10000000));
                        agenceService.createAgence(agenceDTO);
                    });

            // Test création voitures via service
            List<AgenceDTO> agences = agenceService.getAllAgences();

            agences.forEach(agence -> {
                try {
                    // Créer une voiture pour chaque agence
                    VoitureDTO voitureDTO = new VoitureDTO();
                    voitureDTO.setMarque("Renault");
                    voitureDTO.setModele("Clio");
                    voitureDTO.setMatricule("AA-" + (100 + new Random().nextInt(900)) + "-BB");
                    voitureDTO.setPrixParJour(350.0 + Math.random() * 200);
                    voitureDTO.setDateMiseEnService(LocalDate.now().minusMonths((long)(Math.random() * 12)));
                    voitureDTO.setStatut("DISPONIBLE");
                    voitureDTO.setAgenceId(agence.getId());
                    voitureDTO.setNombrePortes(5);
                    voitureDTO.setTypeCarburant("ESSENCE");
                    voitureDTO.setBoiteVitesse("MANUELLE");
                    vehiculeService.createVoiture(voitureDTO);

                    // Créer une moto pour chaque agence
                    MotoDTO motoDTO = new MotoDTO();
                    motoDTO.setMarque("Yamaha");
                    motoDTO.setModele("MT-07");
                    motoDTO.setMatricule("MC-" + (100 + new Random().nextInt(900)) + "-DD");
                    motoDTO.setPrixParJour(250.0 + Math.random() * 100);
                    motoDTO.setDateMiseEnService(LocalDate.now().minusMonths((long)(Math.random() * 12)));
                    motoDTO.setStatut("DISPONIBLE");
                    motoDTO.setAgenceId(agence.getId());
                    motoDTO.setCylindree(689);
                    motoDTO.setTypeMoto("ROADSTER");
                    motoDTO.setCasqueInclus(true);
                    vehiculeService.createMoto(motoDTO);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Afficher les statistiques
            List<VehiculeDTO> vehicules = vehiculeService.getAllVehicules();
            System.out.println("✅ Total véhicules créés : " + vehicules.size());

            long disponibles = vehicules.stream()
                    .filter(v -> v.getStatut().equals("DISPONIBLE"))
                    .count();
            System.out.println("✅ Véhicules disponibles : " + disponibles);

            System.out.println("✅ Tests avec services terminés !");
        };
    }
}