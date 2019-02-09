package io.kevindewit.service.prototype.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TYPE")
public class Prototype_Type extends Prototype_Base {

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
    private Set<Prototype_Type> superEffective = new HashSet<>();

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
    private Set<Prototype_Type> notVeryEffective = new HashSet<>();

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
    private Set<Prototype_Type> noEffect = new HashSet<>();

    public Prototype_Type() {
        super();
    }

    //region getters / setters

    public Set<Prototype_Type> getSuperEffective() {
        return superEffective;
    }

    public void setSuperEffective(Set<Prototype_Type> superEffective) {
        this.superEffective = superEffective;
    }

    public Set<Prototype_Type> getNotVeryEffective() {
        return notVeryEffective;
    }

    public void setNotVeryEffective(Set<Prototype_Type> notVeryEffective) {
        this.notVeryEffective = notVeryEffective;
    }

    public Set<Prototype_Type> getNoEffect() {
        return noEffect;
    }

    public void setNoEffect(Set<Prototype_Type> noEffect) {
        this.noEffect = noEffect;
    }

    //endregion
}
