package uz.itm.restwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.itm.restwarehouse.entity.Supplier;
import uz.itm.restwarehouse.projection.SupplierProjection;

@RepositoryRestResource(path = "supplier",excerptProjection = SupplierProjection.class)
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
}
