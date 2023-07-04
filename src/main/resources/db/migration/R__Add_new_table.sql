DROP TABLE IF EXISTS public.users;
CREATE TABLE public.users (
    id serial4 NOT NULL,
    role_id serial4 NOT NULL,
	email varchar(50) NOT NULL,
	"name" varchar(50) NOT NULL,
	"password" varchar(64) NOT NULL,
	created_at timestamptz NULL DEFAULT now(),
	updated_at timestamptz NULL DEFAULT now(),
	CONSTRAINT uk_name_users UNIQUE (name),
	CONSTRAINT uk_name_email UNIQUE (email),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS public.roles;
CREATE TABLE public.roles (
    id serial4 NOT NULL,
	"name" varchar(50) NOT NULL,
	CONSTRAINT uk_name_roles UNIQUE (name),
	CONSTRAINT roles_pkey PRIMARY KEY (id)
);