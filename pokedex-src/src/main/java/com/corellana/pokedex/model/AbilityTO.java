package com.corellana.pokedex.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.validation.annotation.Validated;

/**
 * AbilityTO
 */
@Validated

@AllArgsConstructor
@NoArgsConstructor
public class AbilityTO   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("hidden")
  private Boolean hidden = null;

  public AbilityTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Nombre de la habilidad
   * @return name
  **/
  @ApiModelProperty(value = "Nombre de la habilidad")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AbilityTO hidden(Boolean hidden) {
    this.hidden = hidden;
    return this;
  }

  /**
   * Es habilidad oculta
   * @return hidden
  **/
  @ApiModelProperty(value = "Es habilidad oculta")


  public Boolean isHidden() {
    return hidden;
  }

  public void setHidden(Boolean hidden) {
    this.hidden = hidden;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AbilityTO abilityTO = (AbilityTO) o;
    return Objects.equals(this.name, abilityTO.name) &&
        Objects.equals(this.hidden, abilityTO.hidden);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, hidden);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AbilityTO {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    hidden: ").append(toIndentedString(hidden)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

