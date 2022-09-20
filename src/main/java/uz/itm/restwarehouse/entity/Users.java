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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"code","phoneNumber"}))
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false,unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private Integer code;

    @Column(nullable = false)
    private String password;

    private boolean active;

    @ManyToMany
    @JoinTable(name = "users_warehouses",
    joinColumns = @JoinColumn(name = "users_id"),
    inverseJoinColumns = @JoinColumn(name = "warehouse_id"))
    private List<Warehouse> warehouses;

    public Users add(Warehouse warehouse){
        this.warehouses.add(warehouse);
        return this;
    }
    public Users remove(Warehouse warehouse){
        this.warehouses.remove(warehouse);
        return this;
    }


}
