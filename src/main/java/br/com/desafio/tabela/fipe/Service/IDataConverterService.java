package br.com.desafio.tabela.fipe.Service;

public interface IDataConverterService {
    <T> T getData(String json, Class<T> classe);

}
