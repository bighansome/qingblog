package com.example.qingblog.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * id属性由外键约束决定
 */
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", columnDefinition = "varchar(64) binary")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "display_name")
    private String displayName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Category(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public Category() {
    }
}
