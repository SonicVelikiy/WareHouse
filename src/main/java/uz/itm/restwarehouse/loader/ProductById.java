package uz.itm.restwarehouse.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.itm.restwarehouse.entity.Product;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductById {
    private boolean success;
    private Product product;
}
