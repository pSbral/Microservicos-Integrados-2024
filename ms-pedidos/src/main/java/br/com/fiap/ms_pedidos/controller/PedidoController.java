package br.com.fiap.ms_pedidos.controller;

import br.com.fiap.ms_pedidos.dto.PedidoDTO;
import br.com.fiap.ms_pedidos.service.PedidoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    // testando a distribuição de carga
    // devolve a porta da instância que está rodando
    @GetMapping("/port")
    public ResponseEntity<String> getPort(@Value("${local.server.port}") String port){
        String msg = String.format("Requisição da Instância recebida na porta: %s", port);
        return ResponseEntity.ok(msg);
    }

    // ... código omitido ...

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findAll(){
        List<PedidoDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable @NotNull Long id){
        PedidoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> insert(@RequestBody @Valid PedidoDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }
}
