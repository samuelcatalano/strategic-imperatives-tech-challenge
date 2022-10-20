create table provider (
    id   bigint auto_increment primary key,
    name varchar(255) not null
);
create table location(
    id      bigint auto_increment primary key,
    city    varchar(255) not null,
    borough varchar(255) not null
);

create table billing(
    id       bigint auto_increment primary key,
    location bigint         not null,
    provider bigint         not null,
    amount   decimal(10, 2) not null,
    constraint fk_billing_location foreign key (location) references location (id),
    constraint fk_billing_provider foreign key (provider) references provider (id)
);