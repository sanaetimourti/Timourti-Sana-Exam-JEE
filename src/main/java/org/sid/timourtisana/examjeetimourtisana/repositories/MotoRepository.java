package org.sid.timourtisana.examjeetimourtisana.repositories;
import com.votre_nom.exam.enums.TypeMoto;
import org.sid.timourtisana.examjeetimourtisana.entities.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {
    List<Moto> findByTypeMoto(TypeMoto typeMoto);
    List<Moto> findByCylindreeGreaterThan(Integer cylindree);
}
