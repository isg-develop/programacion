
-- Database: "Cineuth" se debe crear manuelmente o primero el script de la base y aparte el script de tablas e insertar.

-- DROP DATABASE cineuth;

--CREATE DATABASE cineuth
--  WITH OWNER = postgres
--       ENCODING = 'UTF8'
--       TABLESPACE = pg_default
--       LC_COLLATE = 'Spanish_Spain.1252'
--       LC_CTYPE = 'Spanish_Spain.1252'
--       CONNECTION LIMIT = -1;

-- Table: public.ct_empleados

-- DROP TABLE public.ct_empleados;

CREATE TABLE public.ct_empleados
(
  id serial NOT NULL,
  nombre character varying(150) NOT NULL,
  apellido_paterno character varying(200),
  apellido_materno character varying(200),
  colonia character varying(80),
  municipio character varying(50),
  direccion character varying(80),
  ciudad character varying(50),
  cp character varying(10),
  telefono character varying(20),
  celular character varying(30),
  usuario character varying(100) NOT NULL,
  pass character varying(100) DEFAULT ''::character varying,
  rol character varying(55) DEFAULT ''::character varying,
  CONSTRAINT id PRIMARY KEY (id),  
  CONSTRAINT ct_empleados_usuario_key UNIQUE (usuario)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.ct_empleados
  OWNER TO postgres;
  
  
  
  INSERT INTO public.ct_empleados(
            nombre, usuario, pass, rol)
    VALUES ('Administrador', 'admin', '123', 'Administrador');