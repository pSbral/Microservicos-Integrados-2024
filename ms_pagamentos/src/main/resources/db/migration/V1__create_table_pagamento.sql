CREATE TABLE IF NOT EXISTS tb_pagamento(
    id BIGINT NOT NULL AUTO_INCREMENT,
    valor DECIMAL(38, 2) NOT NULL,
    nome VARCHAR(255),
    numero_do_cartao VARCHAR(255),
    validade VARCHAR(255),
    codigo_de_seguranca VARCHAR(255),
    status ENUM('CANCELADO', 'CONFIRMADO', 'CRIADO') NOT NULL,
    pedido_id BIGINT NOT NULL,
    forma_de_pagamento_id BIGINT NOT NULL,

    PRIMARY KEY (id)
);