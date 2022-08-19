BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	email_address varchar(200) NOT NULL UNIQUE,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

DROP TABLE IF EXISTS public.band;

CREATE TABLE public.band
(
    band_id serial, --integer NOT NULL DEFAULT nextval('"Band_band_id_seq"'::regclass),
    band_name character varying(200) COLLATE pg_catalog."default" NOT NULL,
    band_description character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    band_member character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    band_image character varying(5000) COLLATE pg_catalog."default" NOT NULL,
    manager_id integer NOT NULL,
    CONSTRAINT band_id PRIMARY KEY (band_id),
    CONSTRAINT manager_id FOREIGN KEY (manager_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.band
    OWNER to postgres;

DROP TABLE IF EXISTS public.genre;

CREATE TABLE public.genre
(
    genre_id  serial,--integer NOT NULL DEFAULT nextval('genre_genre_id_seq'::regclass),
    genre_name character varying(100) COLLATE pg_catalog."default" UNIQUE NOT NULL,
    CONSTRAINT genre_pkey PRIMARY KEY (genre_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.genre
    OWNER to postgres;

DROP TABLE IF EXISTS public.band_genre;

CREATE TABLE public.band_genre
(
    band_id integer NOT NULL,
    genre_id integer NOT NULL,
    CONSTRAINT band_id FOREIGN KEY (band_id)
        REFERENCES public.band (band_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT genre_id FOREIGN KEY (genre_id)
        REFERENCES public.genre (genre_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.band_genre
    OWNER to postgres;

DROP TABLE IF EXISTS public.messages;

CREATE TABLE public.messages
(
    message_id serial, --integer NOT NULL DEFAULT nextval('messages_message_id_seq'::regclass),
    message_body character varying(2000) COLLATE pg_catalog."default" NOT NULL,
    message_timestamp timestamp without time zone NOT NULL,
    band_id integer NOT NULL,
    CONSTRAINT messages_pkey PRIMARY KEY (message_id),
    CONSTRAINT band_id FOREIGN KEY (band_id)
        REFERENCES public.band (band_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.messages
    OWNER to postgres;

DROP TABLE IF EXISTS public.user_band;

CREATE TABLE public.user_band
(
    user_id integer NOT NULL,
    band_id integer NOT NULL,
    CONSTRAINT band_id FOREIGN KEY (band_id)
        REFERENCES public.band (band_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT user_id FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_band
    OWNER to postgres;

DROP TABLE IF EXISTS public.venue;

CREATE TABLE public.venue
(
    venue_id serial, --integer NOT NULL DEFAULT nextval('venue_venue_id_seq'::regclass),
    venue_name character varying(200) COLLATE pg_catalog."default" NOT NULL,
    venue_address character varying(200) COLLATE pg_catalog."default" NOT NULL,
    venue_description character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    venue_map character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT venue_pkey PRIMARY KEY (venue_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.venue
    OWNER to postgres;

DROP TABLE IF EXISTS public.show;

CREATE TABLE public.show
(
    show_id serial, --integer NOT NULL DEFAULT nextval('show_show_id_seq'::regclass),
    show_time timestamp without time zone NOT NULL,
    show_title character varying(200) COLLATE pg_catalog."default" NOT NULL,
    show_description character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    venue_id integer NOT NULL,
    CONSTRAINT show_pkey PRIMARY KEY (show_id),
    CONSTRAINT venue_id FOREIGN KEY (venue_id)
            REFERENCES public.venue (venue_id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
            NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.show
    OWNER to postgres;

DROP TABLE IF EXISTS public.show_band;

CREATE TABLE public.show_band
(
    show_id integer NOT NULL,
    band_id integer NOT NULL,
    CONSTRAINT band_id FOREIGN KEY (band_id)
        REFERENCES public.band (band_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT show_id FOREIGN KEY (show_id)
        REFERENCES public.show (show_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.show_band
    OWNER to postgres;

DROP TABLE IF EXISTS public.user_messages;

CREATE TABLE public.user_messages
(
    user_id integer NOT NULL,
    message_id integer NOT NULL,
    CONSTRAINT user_id FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT message_id FOREIGN KEY (message_id)
        REFERENCES public.messages (message_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.band_genre
    OWNER to postgres;

DROP TABLE IF EXISTS public.photo;

CREATE TABLE public.photo
(
    photo_id  serial,--integer NOT NULL DEFAULT nextval('genre_genre_id_seq'::regclass),
    photo_url character varying(2000) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT photo_pkey PRIMARY KEY (photo_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.photo
    OWNER to postgres;

DROP TABLE IF EXISTS public.band_photo;

CREATE TABLE public.band_photo
(
    band_id integer NOT NULL,
    photo_id integer NOT NULL,
    CONSTRAINT band_id FOREIGN KEY (band_id)
        REFERENCES public.band (band_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT photo_id FOREIGN KEY (photo_id)
        REFERENCES public.photo (photo_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.band_photo
    OWNER to postgres;

COMMIT TRANSACTION;
