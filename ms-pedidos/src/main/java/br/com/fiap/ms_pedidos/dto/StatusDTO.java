package br.com.fiap.ms_pedidos.dto;

import br.com.fiap.ms_pedidos.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatusDTO {

    private Status status;
}
