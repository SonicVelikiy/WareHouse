package uz.itm.restwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.itm.restwarehouse.entity.Output;

public interface OutputRepository extends JpaRepository<Output,Integer> {
}
