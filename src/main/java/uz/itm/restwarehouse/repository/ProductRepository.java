package uz.itm.restwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.itm.restwarehouse.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
