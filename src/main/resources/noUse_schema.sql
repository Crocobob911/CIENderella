create table content(
    id              bigint          not null,
    password        varchar    not null,
    status          boolean      not null,
    title           varchar    not null,
    text            varchar    not null,
    primary key (id)
);

create table reason(
    id      bigint          not null,
    text    varchar         not null,
    valid   boolean         not null,
    primary key (id)
);

create table writer(
    id      bigint             not null,
    text    varchar    not null,
    valid   boolean         not null,
    primary key (id)
);