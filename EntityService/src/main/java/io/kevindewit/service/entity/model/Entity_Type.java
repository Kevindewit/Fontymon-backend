package io.kevindewit.service.entity.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@javax.persistence.Entity
@Table(name = "TYPE")
public class Entity_Type extends Entity_Base {

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "name"
    )
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany(fetch = FetchType.EAGER)
    @ApiModelProperty(
            required = true,
            dataType = "Set",
            position = 1,
            example = "[Grass, Bug]"
    )
    private Set<Entity_Type> superEffective = new HashSet<>();

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "name"
    )
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany(fetch = FetchType.EAGER)
    @ApiModelProperty(
            required = true,
            dataType = "Set",
            position = 2,
            example = "[Water]"
    )
    private Set<Entity_Type> notVeryEffective = new HashSet<>();

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "name"
    )
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany(fetch = FetchType.EAGER)
    @ApiModelProperty(
            required = true,
            dataType = "Set",
            position = 3,
            example = "[Fire]"
    )
    private Set<Entity_Type> noEffect = new HashSet<>();

    public Entity_Type() {
        super();
    }

    //region getters / setters

    public Set<Entity_Type> getSuperEffective() {
        return superEffective;
    }

    public void setSuperEffective(Set<Entity_Type> superEffective) {
        this.superEffective = superEffective;
    }

    public Set<Entity_Type> getNotVeryEffective() {
        return notVeryEffective;
    }

    public void setNotVeryEffective(Set<Entity_Type> notVeryEffective) {
        this.notVeryEffective = notVeryEffective;
    }

    public Set<Entity_Type> getNoEffect() {
        return noEffect;
    }

    public void setNoEffect(Set<Entity_Type> noEffect) {
        this.noEffect = noEffect;
    }

    //endregion
}
