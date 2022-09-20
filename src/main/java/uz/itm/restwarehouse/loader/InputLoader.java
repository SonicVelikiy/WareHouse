package uz.itm.restwarehouse.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InputLoader {

    private Timestamp date;

    private Integer warehouseId;

    private Integer supplierId;

    private Integer currencyId;

    private Integer factureNumber;

    private Integer code;
}
