package io.kevindewit.service.modification.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "PARTICIPANT")
public class Modification_Participant {

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
            example = "ExampleUser",
            position = 1
    )
    private String username;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "modifiedBy"
    )
    private List<Modification_Type> typeModifcations;

    public Modification_Participant(String username) {
        this.username = username;
    }

    //region Getters / Setters

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //endregion
}
