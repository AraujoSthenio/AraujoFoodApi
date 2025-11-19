insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Restaurante 1', 5, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Restaurante 2', 5.5, 2);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Restaurante 3', 4.5, 1);

insert into forma_pagamento (descricao) values ('Dinheiro');
insert into forma_pagamento (descricao) values ('Cartão');
insert into forma_pagamento (descricao) values ('Pix');

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

