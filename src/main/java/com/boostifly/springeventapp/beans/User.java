package com.boostifly.springeventapp.beans;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@SQLDelete(sql = "UPDATE user SET deleted=true WHERE id=?")

@FilterDef(
        name = "deletedFilter",
        parameters = @ParamDef(name = "isDeleted", type = "boolean")
)
@Filter(
        name = "deletedFilter",
        condition = "deleted = :isDeleted"
)

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String imageUrl;
    private String geoLocalisation;
    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    // here the relation between Models
    @OneToMany(targetEntity = UserEventDetail.class, mappedBy = "user", cascade = CascadeType.DETACH)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<UserEventDetail> userEventDetails;

    //this attribute will not inserted to database just for rest api use
    @Transient
    private List<Event> events;

    //this attribute will control to show detailed json for rest api for page that need it or not like page of list of users.
    @Transient
    public boolean loadDetail = false;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGeoLocalisation() {
        return geoLocalisation;
    }

    public void setGeoLocalisation(String geoLocalisation) {
        this.geoLocalisation = geoLocalisation;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<UserEventDetail> getUserEventDetails() {
        return userEventDetails;
    }


    public List<Event> getEvents() {
        if (loadDetail) {
            return events;
        }
        return null;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
