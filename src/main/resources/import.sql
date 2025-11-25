insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into forma_pagamento (id, descricao) values (1, 'Dinheiro');
insert into forma_pagamento (id, descricao) values (2, 'Cartão');
insert into forma_pagamento (id, descricao) values (3, 'Pix');

insert into permissao (nome, descricao) values ('listar', 'listar entidades');
insert into permissao (nome, descricao) values ('cadastrar', 'cadastrar entidades');
insert into permissao (nome, descricao) values ('editar', 'editar entidades');

insert into estado (id, nome) values (1, 'MG');
insert into estado (id, nome) values (2, 'SP');
insert into estado (id, nome) values (3, 'RS');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Restaurante 1', 5, 1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (2, 'Restaurante 2', 5.5, 2, 2, '38400-999', 'Rua Cristovao Colombo', '2000', 'Agua Vermelha');
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (3, 'Restaurante 3', 4.5, 1, 3, '38400-999', 'Rua Maria Hilda', '3000', 'Nossa Sra. Aparecida');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

