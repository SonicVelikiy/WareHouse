package uz.itm.restwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.itm.restwarehouse.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByName(String name);
}
