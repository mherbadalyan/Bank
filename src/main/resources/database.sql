create table bank
(
    id   bigint auto_increment,
    name varchar(20) not null,
    type enum('CENTRAL','COMMERCIAL','SPECIALISED') not null,
    constraint bank_pk
        primary key (id)
);

create table address
(
    id      bigint auto_increment,
    country varchar(20) not null,
    city    varchar(20) not null,
    constraint address_pk
        primary key (id)
);

create unique index address_country_city_ui
    on address (country, city);

create table account
(
    id      bigint      not null,
    CH_id   bigint         not null,
    IBAN    varchar(32) not null,
    bank_id bigint      not null,
    balance bigint      null,
    constraint account_pk
        primary key (id),
    constraint account_fk
        foreign key (bank_id) references bank (id)
);

create unique index account_IBAN_uindex
    on account (IBAN);

create table card_holder
(
    id         bigint auto_increment,
    address_id bigint      not null,
    name       varchar(25) not null,
    phone      varchar(15) not null,
    constraint card_holder_pk
        primary key (id),
    constraint card_holder_add_id_fk
        foreign key (address_id) references address (id)
);

create unique index card_holder_phone_uindex
    on card_holder (phone);

create table card
(
    card_number  bigint                        not null,
    type         enum('DEBIT','CREDIT')        not null,
    payment_type enum('MASTER_CARD','VISA','AMERICAN_EXPRESS')   not null,
    balance      bigint      default 0         not null,
    date         date                          not null,
    cvv          int                           not null,
    status       enum('CREATED','ACTIVE','BLOCKED') default 'CREATED' not null,
    pin          varchar(50)                   not null,
    account_id   bigint                        not null,
    constraint card_pk
        primary key (card_number),
    constraint card_acc_id_fk
        foreign key (account_id) references account (id)
);

alter table account
    add constraint account_CH_id_fk
        foreign key (CH_id) references card_holder (id);
