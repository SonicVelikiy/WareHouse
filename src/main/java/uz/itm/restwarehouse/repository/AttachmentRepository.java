package uz.itm.restwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.itm.restwarehouse.entity.Attachment;
import uz.itm.restwarehouse.projection.AttachmentProjection;

import java.util.List;

@RepositoryRestResource(path = "attachment",excerptProjection = AttachmentProjection.class)
public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
    List<Attachment>findAllByProduct_Id(Integer product_id);
}
