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

insert into cidade (nome) values ('Belo Horizonte');
insert into cidade (nome) values ('Formiga');
insert into cidade (nome) values ('São Paulo');

insert into estado (nome) values ('MG');
insert into estado (nome) values ('SP');
insert into estado (nome) values ('RS');