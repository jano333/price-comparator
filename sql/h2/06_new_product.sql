create table PUBLIC.NEW_PRODUCT (
	ID bigint generated by default as identity,
	NAME varchar(255) not null,
	PRODUCT_URL varchar(255) not null,
	PRODUCT_PICTURE_URL varchar(255),
	STATUS varchar(255) not null,
	ESHOP_TYPE varchar(255) not null,
	primary key (ID)
);

alter table PUBLIC.NEW_PRODUCT add constraint UK_1  unique (PRODUCT_URL);
