package uz.itm.restwarehouse.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutputLoader {
    private Timestamp date;

    private Integer warehouseId;

    private Integer clientId;

    private Integer currencyId;

    private Integer factureNumber;

    private Integer code;
}
