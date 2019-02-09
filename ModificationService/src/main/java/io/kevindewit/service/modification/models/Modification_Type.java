package io.kevindewit.service.modification.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "TYPE")
public class Modification_Type extends Modification_Base{

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "name"
    )
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany(fetch = FetchType.EAGER)
    @ApiModelProperty(
            required = true,
            position = 0,
            example = "[Grass : INSERT, Bug : INSERT]"
    )
    private Set<Map<Modification_Type, Modification_Operation>> superEffective = new HashSet<>();

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "name"
    )
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany(fetch = FetchType.EAGER)
    @ApiModelProperty(
            required = true,
            position = 1,
            example = "[Water : INSERT]"
    )
    private Set<Map<Modification_Type, Modification_Operation>> notVeryEffective = new HashSet<>();

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "name"
    )
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany(fetch = FetchType.EAGER)
    @ApiModelProperty(
            required = true,
            position = 2,
            example = "[Fire : DELETE]"
    )
    private Set<Map<Modification_Type, Modification_Operation>> NoEffect = new HashSet<>();

    public Modification_Type() {
        super();
    }

    //region Getters / Setters

    public Set<Map<Modification_Type, Modification_Operation>> getSuperEffective() {
        return superEffective;
    }

    public void setSuperEffective(Set<Map<Modification_Type, Modification_Operation>> superEffective) {
        this.superEffective = superEffective;
    }

    public Set<Map<Modification_Type, Modification_Operation>> getNotVeryEffective() {
        return notVeryEffective;
    }

    public void setNotVeryEffective(Set<Map<Modification_Type, Modification_Operation>> notVeryEffective) {
        this.notVeryEffective = notVeryEffective;
    }

    public Set<Map<Modification_Type, Modification_Operation>> getNoEffect() {
        return NoEffect;
    }

    public void setNoEffect(Set<Map<Modification_Type, Modification_Operation>> noEffect) {
        NoEffect = noEffect;
    }

    //endregion
}
