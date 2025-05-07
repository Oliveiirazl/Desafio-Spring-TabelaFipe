package br.com.desafio.tabela.fipe.Model;

public record ValueFipeModel(  String Valor,
                               String Marca,
                               String Modelo,
                               String Combustivel,
                               String CodigoFipe,
                               String MesReferencia,
                               int AnoModelo,
                               int TipoVeiculo,
                               String SiglaCombustivel) {
}
