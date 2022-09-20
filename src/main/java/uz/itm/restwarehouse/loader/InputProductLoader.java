package uz.itm.restwarehouse.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.itm.restwarehouse.entity.Input;
import uz.itm.restwarehouse.entity.Product;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InputProductLoader {

    private Integer productId;

    private double amount;

    private double price;

    private String expireDate;

    private Integer inputId;
}
