package com.yusufsezer.model;

import com.yusufsezer.contract.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Where;

@Entity
@Where(clause = "deleted = false") // Hibernate feature
public class Category extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String color;
    private String name;
    @Lob
    @Column(length = 1000)
    private String description;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Where(clause = "deleted = false") // Hibernate feature
    private List<Task> tasks = new ArrayList<>();

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return getName();
    }

}
