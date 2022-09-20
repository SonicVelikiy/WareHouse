package uz.itm.restwarehouse.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.itm.restwarehouse.entity.Supplier;

@Projection(types = {Supplier.class})
public interface SupplierProjection {
    Integer getId();
    String getName();
    boolean getActive();
    String getPhoneNumber();
}
