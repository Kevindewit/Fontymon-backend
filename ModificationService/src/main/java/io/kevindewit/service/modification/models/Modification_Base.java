package io.kevindewit.service.modification.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import java.util.Map;
import java.util.UUID;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class Modification_Base {

    @Id
    @Column(name = "ID")
    @ApiModelProperty(
            required = true,
            example = "98da6222-291c-11e9-b210-d663bd873d93",
            position = 0
    )
    private UUID uuid;

    @ApiModelProperty(
            example = "Example",
            position = 1
    )
    private Map<String, Modification_Operation> name;

    @JsonIgnore
    private Modification_Status status;

    @ApiModelProperty(
            required = true,
            example = "98da6222-291c-11e9-b210-d663bd873d93",
            position = 2
    )
    private UUID userUuid;

    @ApiModelProperty(
            example = "98da6222-291c-11e9-b210-d663bd873d93",
            position = 3
    )
    private Map<UUID, Modification_Operation> prototypeUuid;

    public Modification_Base() {
    }

    //region Getters / Setters

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Map<String, Modification_Operation> getName() {
        return name;
    }

    public void setName(Map<String, Modification_Operation> name) {
        this.name = name;
    }

    public Modification_Status getStatus() {
        return status;
    }

    public void setStatus(Modification_Status status) {
        this.status = status;
    }

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }

    public Map<UUID, Modification_Operation> getPrototypeUuid() {
        return prototypeUuid;
    }

    public void setPrototypeUuid(Map<UUID, Modification_Operation> prototypeUuid) {
        this.prototypeUuid = prototypeUuid;
    }

    //endregion
}
