package com.corellana.pokedex.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * PokemonDetailsTO
 */
@Validated


public class PokemonDetailsTO   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("evolutionChain")
  @Valid
  private List<PokemonTO> evolutionChain = null;

  @JsonProperty("varieties")
  @Valid
  private List<PokemonTO> varieties = null;

  public PokemonDetailsTO id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * ID del pokemon
   * @return id
  **/
  @ApiModelProperty(value = "ID del pokemon")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public PokemonDetailsTO name(String name) {
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

  public PokemonDetailsTO description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Descripción del pokemon
   * @return description
  **/
  @ApiModelProperty(value = "Descripción del pokemon")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PokemonDetailsTO evolutionChain(List<PokemonTO> evolutionChain) {
    this.evolutionChain = evolutionChain;
    return this;
  }

  public PokemonDetailsTO addEvolutionChainItem(PokemonTO evolutionChainItem) {
    if (this.evolutionChain == null) {
      this.evolutionChain = new ArrayList<>();
    }
    this.evolutionChain.add(evolutionChainItem);
    return this;
  }

  /**
   * Evoluciones del pokemon
   * @return evolutionChain
  **/
  @ApiModelProperty(value = "Evoluciones del pokemon")

  @Valid

  public List<PokemonTO> getEvolutionChain() {
    return evolutionChain;
  }

  public void setEvolutionChain(List<PokemonTO> evolutionChain) {
    this.evolutionChain = evolutionChain;
  }

  public PokemonDetailsTO varieties(List<PokemonTO> varieties) {
    this.varieties = varieties;
    return this;
  }

  public PokemonDetailsTO addVarietiesItem(PokemonTO varietiesItem) {
    if (this.varieties == null) {
      this.varieties = new ArrayList<>();
    }
    this.varieties.add(varietiesItem);
    return this;
  }

  /**
   * Variantes del pokemon
   * @return varieties
  **/
  @ApiModelProperty(value = "Variantes del pokemon")

  @Valid

  public List<PokemonTO> getVarieties() {
    return varieties;
  }

  public void setVarieties(List<PokemonTO> varieties) {
    this.varieties = varieties;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PokemonDetailsTO pokemonDetailsTO = (PokemonDetailsTO) o;
    return Objects.equals(this.id, pokemonDetailsTO.id) &&
        Objects.equals(this.name, pokemonDetailsTO.name) &&
        Objects.equals(this.description, pokemonDetailsTO.description) &&
        Objects.equals(this.evolutionChain, pokemonDetailsTO.evolutionChain) &&
        Objects.equals(this.varieties, pokemonDetailsTO.varieties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, evolutionChain, varieties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PokemonDetailsTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    evolutionChain: ").append(toIndentedString(evolutionChain)).append("\n");
    sb.append("    varieties: ").append(toIndentedString(varieties)).append("\n");
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

