package uz.itm.restwarehouse.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.itm.restwarehouse.entity.Category;
import uz.itm.restwarehouse.entity.Measurement;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductLoader {

    @NotNull(message = "name should not be empty")
    private String name;

    private Integer categoryId;

    private Integer measurementId;

    private Integer code;

    private boolean active;

}
