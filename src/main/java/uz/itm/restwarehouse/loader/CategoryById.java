package uz.itm.restwarehouse.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.itm.restwarehouse.entity.Category;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryById {
    private boolean success;
    private Category category;
}
