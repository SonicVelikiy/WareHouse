package uz.itm.restwarehouse.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class WarehouseLoader {
    @NotNull(message = "name should not b e empty")
    private String name;

    private boolean active;
}
