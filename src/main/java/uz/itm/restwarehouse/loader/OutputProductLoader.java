package uz.itm.restwarehouse.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutputProductLoader {
    private Integer productId;

    private double amount;

    private double price;

    private Integer inputId;
}
