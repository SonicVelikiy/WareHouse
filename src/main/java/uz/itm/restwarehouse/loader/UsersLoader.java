package uz.itm.restwarehouse.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersLoader {
    @NotNull(message = "First should not be empty")
    private String firstName;
    @NotNull(message = "Last name should not be empty")
    private String lastName;

    @NotNull(message = "phone number should not be empty")
    private String phoneNumber;

    @NotNull(message = "code should not be empty")
    private Integer code;

    @NotNull(message = "password should not be empty")
    private String password;

    private boolean active;
}
