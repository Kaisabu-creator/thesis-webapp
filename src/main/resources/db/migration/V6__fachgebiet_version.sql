alter table fachgebiet
    add version integer not null default 0;

alter table fachgebiet
    alter column version drop default;