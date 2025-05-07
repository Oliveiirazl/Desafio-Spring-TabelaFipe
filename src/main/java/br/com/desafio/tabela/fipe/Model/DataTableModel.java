package br.com.desafio.tabela.fipe.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataTableModel(
        @JsonAlias("codigo") Integer codigo,
        @JsonAlias("nome") String nome
) {}
