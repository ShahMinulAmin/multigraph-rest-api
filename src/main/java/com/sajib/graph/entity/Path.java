package com.sajib.graph.entity;

import javax.persistence.*;

/**
 * Created by sajib on 2/19/19.
 */
@Entity
@Table(name = "path")
public class Path {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", scale = 0)
    private Integer id;

    @OneToOne(targetEntity = City.class)
    @JoinColumn(name = "from_city", referencedColumnName = "id")
    private City fromCity;

    @OneToOne(targetEntity = City.class)
    @JoinColumn(name = "to_city", referencedColumnName = "id")
    private City toCity;

    @Basic
    @Column(name = "container_size")
    private Integer container;

    @Basic
    @Column(name = "transport_type", length = 256)
    private String transportType;

    @Basic
    @Column(name = "duration")
    private Integer duration;

    @Basic
    @Column(name = "cost")
    private Integer cost;

    public Path() {

    }

    public Path(City fromCity, City toCity, Integer container, String transportType,
                Integer duration, Integer cost) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.container = container;
        this.transportType = transportType;
        this.duration = duration;
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public City getFromCity() {
        return fromCity;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public City getToCity() {
        return toCity;
    }

    public void setToCity(City toCity) {
        this.toCity = toCity;
    }

    public Integer getContainer() {
        return container;
    }

    public void setContainer(Integer container) {
        this.container = container;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}

