package uz.itm.restwarehouse.repository;

import org.apache.catalina.util.Introspection;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.itm.restwarehouse.entity.OutputProduct;

import java.util.List;

public interface OutputProductRepository extends JpaRepository<OutputProduct, Integer> {
    List<OutputProduct>findAllByOutput_Id(Integer id);
}
