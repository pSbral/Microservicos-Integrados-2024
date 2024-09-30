package br.com.fiap.ms_pedidos.service;

import br.com.fiap.ms_pedidos.dto.PedidoDTO;
import br.com.fiap.ms_pedidos.model.Pedido;
import br.com.fiap.ms_pedidos.model.Status;
import br.com.fiap.ms_pedidos.repositories.PedidoRepository;
import br.com.fiap.ms_pedidos.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<PedidoDTO> findAll() {

        return repository.findAll().stream()
                .map(pedido -> modelMapper.map(pedido, PedidoDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id){
        Pedido entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
        return modelMapper.map(entity, PedidoDTO.class);
    }

    @Transactional
    public PedidoDTO insert(PedidoDTO dto){
        Pedido entity = modelMapper.map(dto, Pedido.class);
        entity.setDataHora(LocalDateTime.now());
        entity.setStatus(Status.REALIZADO);
        entity.getItens().forEach(item -> item.setPedido(entity));
        Pedido finalEntity  = repository.save(entity);

        return modelMapper.map(finalEntity, PedidoDTO.class);
    }

}
