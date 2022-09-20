package uz.itm.restwarehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Supplier {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)private Integer id;

    @Column(nullable = false)
    private String name;

    private boolean active;

    @Column(unique = true,nullable = false)
    private String phoneNumber;


}
