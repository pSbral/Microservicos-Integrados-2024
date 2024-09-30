package br.com.fiap.ms_pedidos.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Long id) {
        super("Recurso não encontrado: ID " + id);
    }
}
