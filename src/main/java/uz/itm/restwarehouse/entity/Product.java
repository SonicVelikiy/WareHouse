package uz.itm.restwarehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Measurement measurement;

    private Integer code;

    private boolean active;

    @OneToMany(mappedBy = "product")
    private Set<Attachment> photoIds;

    public void addAttachment(Attachment attachment){
        this.photoIds.add(attachment);
    }
    public void removeAttachment(Attachment attachment){
        this.photoIds.remove(attachment);
    }

}
