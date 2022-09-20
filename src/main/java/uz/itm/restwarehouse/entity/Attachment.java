package uz.itm.restwarehouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Attachment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)private Integer id;

    @Column(nullable = false)
    private String fileOriginalName;

    @Column(nullable = false)
    private long size;

    @Column(nullable = false)
    private String contentType;
    @ManyToOne
    @JoinColumn( name = "product_id")
    private Product product;
}
