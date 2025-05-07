package br.com.desafio.tabela.fipe.Service;

public interface IConsumptionApiService {
    <T> T obterDados(String json, Class<T> classe);
}
