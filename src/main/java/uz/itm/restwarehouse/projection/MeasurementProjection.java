package uz.itm.restwarehouse.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.itm.restwarehouse.entity.Measurement;

@Projection(types = {Measurement.class})
public interface MeasurementProjection {

    public String getId();
    public String getName();
    public String getActive();
}
