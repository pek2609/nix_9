package ua.com.alevel.web.dto;

import java.util.Date;

public abstract class DtoResponse {

    private Long id;
    private Date created;
    private Date updated;
    private Boolean visible;

    public DtoResponse(Long id, Date created, Date updated, Boolean visible) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.visible = visible;
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

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
