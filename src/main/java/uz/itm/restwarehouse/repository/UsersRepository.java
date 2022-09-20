package uz.itm.restwarehouse.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.itm.restwarehouse.entity.Users;
public interface UsersRepository extends JpaRepository<Users,Integer> {
    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);

    boolean existsByPhoneNumber(String phoneNumber);

}
