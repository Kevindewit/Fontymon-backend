package io.kevindewit.service.prototype.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Prototype_Base {

    @Id
    @Column(name = "ID")
    @ApiModelProperty(
            required = true,
            example = "98da6222-291c-11e9-b210-d663bd873d93",
            position = 0
    )
    private UUID uuid;

    @Column(unique = true)
    @ApiModelProperty(
            required = true,
            example = "Example"
    )
    private String name;

    @JsonIgnore
    private Prototype_Status status;

    public Prototype_Base() {
    }

    //region Getters / Setters

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Prototype_Status getStatus() {
        return status;
    }

    public void setStatus(Prototype_Status status) {
        this.status = status;
    }

    //endregion

}
