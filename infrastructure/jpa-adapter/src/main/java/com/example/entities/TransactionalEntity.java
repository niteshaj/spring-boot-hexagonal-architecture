package com.example.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class TransactionalEntity implements Serializable {
    private static final long serialVersionUID = 2487002187064246211L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void beforePersist() {
        setCreatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void beforeUpdate() {
        setUpdatedAt(LocalDateTime.now());
    }

    public boolean equals(Object that) {
        if (that == null) {
            return false;
        }
        if (this.getClass().equals(that.getClass())) {
            TransactionalEntity thatTE = (TransactionalEntity) that;
            return this.getId() != null && thatTE.getId() != null && this.getId().equals(thatTE.getId());
        }
        return false;
    }

    public int hashCode() {
        if (getId() == null) {
            return -1;
        }
        return getId().hashCode();
    }
}
