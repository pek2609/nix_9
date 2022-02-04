package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity {

    public BaseEntity(Long id, Date created, Date updated) {
        this.id = id;
        this.created = created;
        this.updated = updated;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    public BaseEntity() {
        this.created = new Date();
        this.updated = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.updated = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
