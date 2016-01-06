create table PUBLIC.ESHOP (
	ID bigint generated by default as identity,
	HOME_PAGE varchar(255),
	NAME varchar(255) not null,
	PARSER_CLASS_NAME varchar(255) not null,
	primary key (ID)
);

create table PUBLIC.CATEGORY (
	ID bigint generated by default as identity,
	NAME varchar(255) not null,
	PARENT_CATEGORY_ID bigint,
	primary key (ID)
);

create table PUBLIC.PRODUCT (
	ID bigint generated by default as identity,
	COUNT_OF_ITEM_IN_ONE_PACKAGE integer not null,
	COUNT_OF_UNIT decimal(19,2) not null,
	GOOD_PRICE decimal(19,2),
	IMAGE_PATH varchar(255),
	NAME varchar(255) not null,
	UNIT varchar(255) not null,
	CATEGORY_ID bigint,
	primary key (ID)
);

create table PUBLIC.PRODUCT_IN_ESHOP (
	ID bigint generated by default as identity,
	PRODUCT_PAGE_IN_ESHOP varchar(255) not null,
	ESHOP_ID bigint not null,
	PRODUCT_ID bigint not null,
	primary key (ID)
);

create table PUBLIC.GROUP_OF_PRODUCT (
	ID bigint generated by default as identity,
	NAME varchar(255) not null,
	primary key (ID)
);

create table PUBLIC.GROUP_PRODUCT (
	GROUP_ID bigint not null,
	PRODUCT_ID bigint not null
);

alter table PUBLIC.ESHOP
	add constraint UK_29vp4iccilnv64rfkd3c6twmf  unique (NAME);

alter table PUBLIC.ESHOP
	add constraint UK_2k5xhr0w26e9soombrod53xu2  unique (PARSER_CLASS_NAME);

alter table PUBLIC.CATEGORY
	add constraint UK_riore06il3igyibh7h1ksq64p  unique (NAME);

alter table PUBLIC.PRODUCT
	add constraint UK_lh7nqls96dm61kob7cd8d00s3  unique (NAME);

alter table PUBLIC.PRODUCT_IN_ESHOP
	add constraint UK_4c5jyo1q55ga7mfu7hkj34b6o  unique (PRODUCT_PAGE_IN_ESHOP);

alter table PUBLIC.GROUP_OF_PRODUCT
	add constraint UK_90m6h6wjuij15fohidn02cuwt  unique (NAME);

alter table PUBLIC.GROUP_PRODUCT
	add constraint UK_ndk1slvlcn3pn92tuth5o3ij5  unique (GROUP_ID, PRODUCT_ID);

alter table PUBLIC.CATEGORY
	add constraint FK_nhdxv2ld2v6ayxq7d1fot1tjh
	foreign key (PARENT_CATEGORY_ID)
	references PUBLIC.CATEGORY;

alter table PUBLIC.PRODUCT
	add constraint FK_6t6fwpoax5485lfta1ha89rlh
	foreign key (CATEGORY_ID)
	references PUBLIC.CATEGORY;

alter table PUBLIC.PRODUCT_IN_ESHOP
	add constraint FK_s9iy0eymur4xyjvkxk2iej9df
	foreign key (ESHOP_ID)
	references PUBLIC.ESHOP;

alter table PUBLIC.PRODUCT_IN_ESHOP
	add constraint FK_fou0fqee1mqrvpdgj83vo38ou
	foreign key (PRODUCT_ID)
	references PUBLIC.PRODUCT;

alter table PUBLIC.GROUP_PRODUCT
	add constraint FK_kbedu71sf0q2c96tfciodkrpr
	foreign key (PRODUCT_ID)
	references PUBLIC.PRODUCT;

alter table PUBLIC.GROUP_PRODUCT
	add constraint FK_nfw8crtdc7mkat3x8aih7dnv6
	foreign key (GROUP_ID)
	references PUBLIC.GROUP_OF_PRODUCT;