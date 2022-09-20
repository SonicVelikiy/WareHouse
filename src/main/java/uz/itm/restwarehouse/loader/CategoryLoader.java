package uz.itm.restwarehouse.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryLoader {
    @NotNull(message = "name should not be empty")
    private String name;

    private Integer parentCategoryId;

    private boolean active;
}
