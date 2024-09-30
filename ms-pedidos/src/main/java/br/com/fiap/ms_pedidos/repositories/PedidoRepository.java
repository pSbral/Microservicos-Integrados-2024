package br.com.fiap.ms_pedidos.repositories;

import br.com.fiap.ms_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
