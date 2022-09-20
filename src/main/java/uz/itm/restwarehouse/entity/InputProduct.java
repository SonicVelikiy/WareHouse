package uz.itm.restwarehouse.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class InputProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)private Integer id;

    @OneToOne
    private Product product;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private double price;

    private String expireDate;

    @ManyToOne
    private Input input;
}
