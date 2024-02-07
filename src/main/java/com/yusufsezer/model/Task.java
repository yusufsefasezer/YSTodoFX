package com.yusufsezer.model;

import com.yusufsezer.contract.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import org.hibernate.annotations.Where;

@Entity
@Where(clause = "deleted = false") // Hibernate feature
public class Task extends BaseEntity implements Serializable {

    public enum Status {
        UNCOMPLETED,
        COMPLETED
    };

    private static final long serialVersionUID = 1L;

    private Status status;
    private String name;
    @Lob
    @Column(length = 1000)
    private String description;

    @OneToOne
    private Category category;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
