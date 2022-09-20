package uz.itm.restwarehouse.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.itm.restwarehouse.entity.Warehouse;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WarehouseById {
    private boolean success;
    private Warehouse warehouse;
}
