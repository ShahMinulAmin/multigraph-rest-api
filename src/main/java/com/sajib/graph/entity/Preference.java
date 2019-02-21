package com.sajib.graph.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by sajib on 2/22/19.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "preference")
public class Preference {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", scale = 0)
    private Integer id;

    @Basic
    @Column(name = "name", length = 1024)
    private String name;

    @Basic
    @Column(name = "value", length = 1024)
    private String value;
}
