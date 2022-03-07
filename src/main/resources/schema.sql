CREATE TABLE tipos
(
    id_tipo integer NOT NULL,
    tipo character varying(30) NOT NULL,
    CONSTRAINT pk_tipos PRIMARY KEY (id_tipo)
);

CREATE TABLE habilidades
(
    id_habilidad integer NOT NULL,
    habilidad character varying(30) NOT NULL,
    CONSTRAINT pk_habilidades PRIMARY KEY (id_habilidad)
);

CREATE TABLE pokemons
(
    id_pokemon integer NOT NULL,
    nombre character varying(30) NOT NULL,
    tipo1 character varying(30),
    tipo2 character varying(30),
    habilidad1 character varying(30),
    habilidad2 character varying(30),
    descripcion character varying(1000),
    CONSTRAINT pk_pokemons PRIMARY KEY (id_pokemon),
    CONSTRAINT fk_pok_tipo1 FOREIGN KEY (tipo1)
        REFERENCES tipos (tipo) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_pok_tipo2 FOREIGN KEY (tipo2)
        REFERENCES tipos (tipo) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_pok_hab1 FOREIGN KEY (habilidad1)
        REFERENCES habilidades (habilidad)  MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_pok_hab2 FOREIGN KEY (habilidad2)
        REFERENCES habilidades (habilidad)  MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);