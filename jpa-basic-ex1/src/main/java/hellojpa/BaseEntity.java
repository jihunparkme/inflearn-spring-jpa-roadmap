package hellojpa;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    private String createdBy;
    private LocalDateTime createdDateTime;
    private String modifiedBy;
    private LocalDateTime modifiedDateTime;
}
