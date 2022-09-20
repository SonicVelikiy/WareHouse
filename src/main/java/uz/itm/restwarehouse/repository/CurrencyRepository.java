package uz.itm.restwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.itm.restwarehouse.entity.Currency;
import uz.itm.restwarehouse.projection.CurrencyProjection;

@RepositoryRestResource(path = "currency",excerptProjection = CurrencyProjection.class)
public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
}
