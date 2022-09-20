package uz.itm.restwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.itm.restwarehouse.entity.InputProduct;

import java.util.List;

public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {
    List<InputProduct>findAllByInput_Id(Integer input_id);
}
