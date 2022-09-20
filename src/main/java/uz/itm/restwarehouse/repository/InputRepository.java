package uz.itm.restwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.itm.restwarehouse.entity.Input;

public interface InputRepository extends JpaRepository<Input,Integer> {
}
