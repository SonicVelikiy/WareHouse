package uz.itm.restwarehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Measurement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;

    @Column(nullable = false)
    private String name;

    private boolean active;
}
