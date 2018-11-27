drop table konto;

create table konto (
  kontonr varchar(15) not null,
  navn    varchar(30) not null,
  saldo   decimal     not null,
  primary key (kontonr)
);

insert into konto
values ('123456', 'Ole Ås', 1000);
insert into konto
values ('345678', 'Anne Grethe �s', 20000);
insert into konto
values ('678909', 'Jonny Hansen', 10000);