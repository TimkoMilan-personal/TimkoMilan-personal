
CREATE TABLE IF NOT EXISTS public.category (
	id int8 NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT category_pkey PRIMARY KEY (id)
);



CREATE TABLE IF NOT EXISTS public.orders (
	id int8 NOT NULL,
	confirm_order_date timestamp NULL,
	create_record_date timestamp NULL,
	is_ordered bool NOT NULL,
	product_id int8 NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS public."role" (
	id serial NOT NULL,
	CONSTRAINT role_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS public.users (
	id int8 NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS public.vendor (
	id int8 NOT NULL,
	date_creation timestamp NULL,
	is_ordered bool NULL,
	product_id int8 NULL,
	CONSTRAINT vendor_pkey PRIMARY KEY (id)
);


-- public.product definition

-- Drop table

-- DROP TABLE public.product cascade;

CREATE TABLE IF NOT EXISTS public.product (
	id int8 NOT NULL,
	count int4 NULL,
	id_code varchar(255) NULL,
	"name" varchar(255) NULL,
	note varchar(255) NULL,
	place varchar(255) NULL,
	category_id int8 NULL,
	CONSTRAINT product_pkey PRIMARY KEY (id),
	CONSTRAINT fk1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id) REFERENCES category(id)
);