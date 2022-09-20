package uz.itm.restwarehouse.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class AttachmentContent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private byte[] mainContent;

    @OneToOne
    private Attachment attachment;


}
