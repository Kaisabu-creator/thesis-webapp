alter table thema
add version integer not null default 0;

alter table thema
    alter column version drop default;