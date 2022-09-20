package uz.itm.restwarehouse.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.itm.restwarehouse.entity.Currency;

@Projection(types = {Currency.class})
public interface CurrencyProjection {
    String getId();
    String getName();
    String getActive();
}
