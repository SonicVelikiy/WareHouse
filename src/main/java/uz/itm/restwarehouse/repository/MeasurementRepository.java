package uz.itm.restwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.itm.restwarehouse.entity.Measurement;
import uz.itm.restwarehouse.projection.MeasurementProjection;

@RepositoryRestResource(path = "measurement",excerptProjection = MeasurementProjection.class)
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
}
