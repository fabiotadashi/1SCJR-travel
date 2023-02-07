create table TB_DESTINATION(
    id bigint primary key auto_increment,
    name varchar(100),
    airport varchar(50),
    country varchar(50),
    description varchar(300),
    price numeric(38,2),
    currency varchar(3),
    created_date timestamp not null,
    last_modified_date timestamp
)