package uz.itm.restwarehouse.projection;

import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.core.config.Projection;
import uz.itm.restwarehouse.entity.Client;

@Projection(types = {Client.class})
public interface ClientProjection {
    String getId();
    String getName();
    String getPhoneNumber();
}
