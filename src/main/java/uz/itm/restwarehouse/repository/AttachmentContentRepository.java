package uz.itm.restwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.itm.restwarehouse.entity.AttachmentContent;

import java.util.List;
import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {

    Optional<AttachmentContent>findAttachmentContentByAttachment_Id(Integer attachment_id);

}
