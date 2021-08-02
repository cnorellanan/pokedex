package com.corellana.pokedex.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * ListPokemonTO
 */
@Validated


public class PokemonListTO   {
  @JsonProperty("limit")
  private Integer limit = null;

  @JsonProperty("offset")
  private Integer offset = null;

  @JsonProperty("total")
  private Integer total = null;

  @JsonProperty("results")
  @Valid
  private List<PokemonTO> results = null;

  public PokemonListTO limit(Integer limit) {
    this.limit = limit;
    return this;
  }

  /**
   * Cantidad de resultados
   * @return limit
  **/
  @ApiModelProperty(value = "Cantidad de resultados")


  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public PokemonListTO offset(Integer offset) {
    this.offset = offset;
    return this;
  }

  /**
   * Comienzo de resultados
   * @return offset
  **/
  @ApiModelProperty(value = "Comienzo de resultados")


  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public PokemonListTO total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * Total de resultados
   * @return total
  **/
  @ApiModelProperty(value = "Total de resultados")


  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public PokemonListTO results(List<PokemonTO> results) {
    this.results = results;
    return this;
  }

  public PokemonListTO addResultsItem(PokemonTO resultsItem) {
    if (this.results == null) {
      this.results = new ArrayList<>();
    }
    this.results.add(resultsItem);
    return this;
  }

  /**
   * Lista de pokemones
   * @return results
  **/
  @ApiModelProperty(value = "Lista de pokemones")

  @Valid

  public List<PokemonTO> getResults() {
    return results;
  }

  public void setResults(List<PokemonTO> results) {
    this.results = results;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PokemonListTO listPokemonTO = (PokemonListTO) o;
    return Objects.equals(this.limit, listPokemonTO.limit) &&
        Objects.equals(this.offset, listPokemonTO.offset) &&
        Objects.equals(this.total, listPokemonTO.total) &&
        Objects.equals(this.results, listPokemonTO.results);
  }

  @Override
  public int hashCode() {
    return Objects.hash(limit, offset, total, results);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListPokemonTO {\n");
    
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
    sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    results: ").append(toIndentedString(results)).append("\n");
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

