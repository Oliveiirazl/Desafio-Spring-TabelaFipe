package br.com.desafio.tabela.fipe.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataTableModel(
        @JsonAlias("codigo") String codigo,
        @JsonAlias("nome") String nome,
        @JsonAlias("modelos") String modelos
) {}
