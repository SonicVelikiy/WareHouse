package uz.itm.restwarehouse.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.itm.restwarehouse.entity.Attachment;

@Projection(types = {Attachment.class})
public interface AttachmentProjection {
    String getId();
    String getName();
    String getSize();
    String getContentType();
}
