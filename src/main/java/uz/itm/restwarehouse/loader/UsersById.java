package uz.itm.restwarehouse.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.itm.restwarehouse.entity.Users;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UsersById {
    private boolean success;
    private Users user;
}
