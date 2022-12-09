create schema posto;

Use posto;

create table tb_funcionairo (
id_func int not null auto_increment,
nome_func varchar (45) not null,
sobrenome varchar (100) not null,
cpf_func char (11) not null,
pis_func char (11) not null,
salario_func float null,
departamento_func varchar (45) not null,
email_func varchar(100) not null,
telefone_func varchar (15) null,
endereco_func varchar (350) null,
primary key (id_func));

create table tb_fornecedor (
id_forn int not null auto_increment,
nome_forn varchar (200) not null,
cnpj char (14) not null,
email_forn varchar(100) not null,
telefone_forn varchar (15) null,
endereco_forn varchar (350) null,
primary key (id_forn));

create table tb_combustivel (
id_combustivel int not null auto_increment,
tipo_combustivel varchar (45) not null,
primary key (id_combustivel));

create table tb_produto (
id_prod int not null auto_increment,
nome_prod varchar (100) not null,
codigo_prod char (13) not null,
quantidade_prod float not null,
valor_prod float not null,
id_combustivel int null,
primary key (id_prod),
foreign key (id_combustivel) references tb_combustivel (id_combustivel));

create table tb_pagamento (
id_pagamento int not null auto_increment,
tipo_pagamento varchar (45) not null,
primary key (id_pagamento));

Create table tb_cliente (
id_cli int not null auto_increment,
nome_cli varchar (45) not null,
sobrenome varchar (100) not null,
cpf_cli char (11) not null,
email_tb_clientecli varchar (100) not null,
telefone_cli varchar (15) null,
endereco_cli varchar (350) null,
primary key (id_cli));

create table tb_pedido (
id_pedido int not null auto_increment,
id_pagamento int not null,
id_prod int not null,
id_cli int not null,
nome_pedido varchar (100) not null,
quantidade_pedido float not null,
valor_pedido float not null,
primary key (id_pedido),
foreign key (id_pagamento) references tb_pagamento (id_pagamento),
foreign key (id_prod) references tb_produto (id_prod),
foreign key (id_cli) references tb_cliente (id_cli));

