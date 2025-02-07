insert into pdv.login (usuario, acessos, iduser, senha, user_type) values ('fabio.admin', null, 0, '$2a$10$uVnXsSKOGo0ECRetdvn2suAbh.wZoD9og7twpobPya2QpC0avJWk6','ADMIN');
insert into  pdv.login  (usuario, acessos, iduser, senha, user_type) values ('fabio.func', null, 0, '$2a$10$uVnXsSKOGo0ECRetdvn2suAbh.wZoD9og7twpobPya2QpC0avJWk6','FUNCIONARIO');
insert into  pdv.login  (usuario, acessos, iduser, senha, user_type) values ('fabio.cli', null, 0, '$2a$10$uVnXsSKOGo0ECRetdvn2suAbh.wZoD9og7twpobPya2QpC0avJWk6','CLIENTE');

insert into pagamento(idformapagamento,descricao,status) values (1,'Cartao Credito', 'S');
insert into pagamento(idformapagamento,descricao,status) values (2,'Cartao Debito', 'S');