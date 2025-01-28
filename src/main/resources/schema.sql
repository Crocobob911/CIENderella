create table content(
    id bigint not null,
    title varchar(255) not null,
    content varchar(255) not null,
    primary key (id)
);

create table reason(
    id bigint not null,
    reason varchar(255) not null,
    valid tinyint(1) not null,
    primary key (id)
);

create table writer(
    id bigint not null,
    reason varchar(255) not null,
    valid tinyint(1) not null,
    primary key (id)
);