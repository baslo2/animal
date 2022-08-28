CREATE SEQUENCE public.t_animal_id_seq
	START WITH 1
	INCREMENT BY 1
	MAXVALUE 999999
	NO MINVALUE
	CACHE 1;

ALTER SEQUENCE public.t_animal_id_seq OWNER TO test_user;