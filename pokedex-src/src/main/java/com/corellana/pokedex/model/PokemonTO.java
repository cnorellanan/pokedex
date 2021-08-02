package com.corellana.pokedex.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * PokemonTO
 */
@Validated


public class PokemonTO   {
  @JsonProperty("id")
  protected Integer id = null;

  @JsonProperty("image")
  protected String image = null;

  @JsonProperty("name")
  protected String name = null;

  @JsonProperty("type")
  @Valid
  protected List<String> type = null;

  @JsonProperty("weight")
  protected Integer weight = null;

  @JsonProperty("abilities")
  @Valid
  protected List<AbilityTO> abilities = null;

  protected PokemonTO id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * ID del pokemon
   * @return id
  **/
  @ApiModelProperty(value = "ID del pokemon")

  @Valid

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public PokemonTO image(String image) {
    this.image = image;
    return this;
  }

  /**
   * URL imagen pokemon
   * @return image
  **/
  @ApiModelProperty(value = "URL imagen pokemon")


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public PokemonTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Nombre del pokemon
   * @return name
  **/
  @ApiModelProperty(value = "Nombre del pokemon")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PokemonTO type(List<String> type) {
    this.type = type;
    return this;
  }

  public PokemonTO addTypeItem(String typeItem) {
    if (this.type == null) {
      this.type = new ArrayList<>();
    }
    this.type.add(typeItem);
    return this;
  }

  /**
   * Tipos del pokemon
   * @return type
  **/
  @ApiModelProperty(value = "Tipos del pokemon")


  public List<String> getType() {
    return type;
  }

  public void setType(List<String> type) {
    this.type = type;
  }

  public PokemonTO weight(Integer weight) {
    this.weight = weight;
    return this;
  }

  /**
   * Peso del pokemon
   * @return weight
  **/
  @ApiModelProperty(value = "Peso del pokemon")

  @Valid

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public PokemonTO abilities(List<AbilityTO> abilities) {
    this.abilities = abilities;
    return this;
  }

  public PokemonTO addAbilitiesItem(AbilityTO abilitiesItem) {
    if (this.abilities == null) {
      this.abilities = new ArrayList<>();
    }
    this.abilities.add(abilitiesItem);
    return this;
  }

  /**
   * Habilidades del pokemon
   * @return abilities
  **/
  @ApiModelProperty(value = "Habilidades del pokemon")

  @Valid

  public List<AbilityTO> getAbilities() {
    return abilities;
  }

  public void setAbilities(List<AbilityTO> abilities) {
    this.abilities = abilities;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PokemonTO pokemonTO = (PokemonTO) o;
    return Objects.equals(this.id, pokemonTO.id) &&
        Objects.equals(this.image, pokemonTO.image) &&
        Objects.equals(this.name, pokemonTO.name) &&
        Objects.equals(this.type, pokemonTO.type) &&
        Objects.equals(this.weight, pokemonTO.weight) &&
        Objects.equals(this.abilities, pokemonTO.abilities);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, image, name, type, weight, abilities);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PokemonTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    weight: ").append(toIndentedString(weight)).append("\n");
    sb.append("    abilities: ").append(toIndentedString(abilities)).append("\n");
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

