package uz.itm.restwarehouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private boolean active;
    @JsonIgnore
    @ManyToMany(mappedBy = "warehouses")
    private List<Users> users;

    public Warehouse add(Users users){
        this.users.add(users);
        return this;
    }
    public  Warehouse remove(Users users){
        this.users.remove(users);
        return this;
    }
}
