package uz.itm.restwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.itm.restwarehouse.entity.Warehouse;



public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);

}
