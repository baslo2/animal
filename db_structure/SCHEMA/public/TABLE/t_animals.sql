CREATE TABLE public.t_animals (
	id integer DEFAULT nextval('public.t_animal_id_seq'::regclass) NOT NULL,
	type character varying(40) NOT NULL,
	age integer NOT NULL,
	name character varying(40) NOT NULL
);

ALTER TABLE public.t_animals OWNER TO test_user;
