package io.kevindewit.service.entity.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Entity_Base {

    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @ApiModelProperty(
            required = true,
            example = "98da6222-291c-11e9-b210-d663bd873d93",
            position = 0
    )
    private UUID uuid;

    @Column(unique = true)
    @ApiModelProperty(
            required = true,
            example = "Example",
            position = 1
    )
    private String name;

    public Entity_Base() {
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

    //endregion

}
