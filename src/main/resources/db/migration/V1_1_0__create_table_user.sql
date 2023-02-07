create table TB_USER(
    id bigint primary key auto_increment,
    username varchar(50),
    password varchar(200),
    created_date timestamp not null,
    last_modified_date timestamp
)