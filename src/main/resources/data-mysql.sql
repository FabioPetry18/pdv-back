insert into login (usuario, acessos, id_user, senha, user_type) values ('fabio.admin', null, 0, '$2a$10$XKuN8LC9letLXuGwlW.U6eLzzq/b0pkGY0eVck1xhKgs62UBIUcFm','ADMIN');
insert into login (usuario, acessos, id_user, senha, user_type) values ('fabio.func', null, 0, '$2a$10$XKuN8LC9letLXuGwlW.U6eLzzq/b0pkGY0eVck1xhKgs62UBIUcFm','FUNCIONARIO');
insert into login (usuario, acessos, id_user, senha, user_type) values ('fabio.cli', null, 0, '$2a$10$XKuN8LC9letLXuGwlW.U6eLzzq/b0pkGY0eVck1xhKgs62UBIUcFm','CLIENTE');

insert into pagamento(idformapagamento,descricao,status) values (1,'Cartao Credito', 'S');
insert into pagamento(idformapagamento,descricao,status) values (2,'Cartao Debito', 'S');