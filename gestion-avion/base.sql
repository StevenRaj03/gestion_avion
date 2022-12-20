CREATE SEQUENCE admin_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE admin_id_seq1 START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE assurance_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE avion_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE entretien_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE kilometrage_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE marque_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE modele_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE sequence_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE utilisateur_id_seq START WITH 1 INCREMENT BY 1;

CREATE  TABLE "admin" ( 
	id                   integer DEFAULT nextval('admin_id_seq1'::regclass) NOT NULL  ,
	email                varchar(100)    ,
	mdp                  varchar(255)    ,
	nom                  varchar(100)    ,
	CONSTRAINT pk_tbl PRIMARY KEY ( id )
 );

CREATE  TABLE marque ( 
	id                   integer DEFAULT nextval('marque_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(80)    ,
	CONSTRAINT pk_avion PRIMARY KEY ( id )
 );

CREATE  TABLE modele ( 
	id                   integer DEFAULT nextval('modele_id_seq'::regclass) NOT NULL  ,
	marque_id            integer    ,
	nom                  varchar(80)    ,
	CONSTRAINT pk_modele PRIMARY KEY ( id )
 );

CREATE  TABLE type_entretien ( 
	id                   integer  NOT NULL  ,
	"type"               varchar(80)    ,
	CONSTRAINT pk_type_entretien PRIMARY KEY ( id )
 );

CREATE  TABLE utilisateur ( 
	id                   integer DEFAULT nextval('utilisateur_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(100)    ,
	prenom               varchar(100)    ,
	email                varchar(80)    ,
	mot_de_passe         varchar(100)    ,
	CONSTRAINT pk_utilisateur PRIMARY KEY ( id )
 );

CREATE  TABLE avion ( 
	id                   integer DEFAULT nextval('avion_id_seq'::regclass) NOT NULL  ,
	modele_id            integer    ,
	matricule            varchar(100)    ,
	image                bytea    ,
	CONSTRAINT pk_avion_0 PRIMARY KEY ( id ),
	CONSTRAINT unq_avion_numeromatricule UNIQUE ( matricule ) 
 );

CREATE  TABLE entretien ( 
	id                   integer DEFAULT nextval('entretien_id_seq'::regclass) NOT NULL  ,
	avion_id             integer    ,
	"date"               date    ,
	type_entretien_id    integer    ,
	CONSTRAINT pk_entretient PRIMARY KEY ( id )
 );

CREATE  TABLE kilometrage ( 
	id                   integer DEFAULT nextval('kilometrage_id_seq'::regclass) NOT NULL  ,
	avion_id             integer    ,
	"date"               date DEFAULT CURRENT_DATE   ,
	kilometrage          integer    ,
	CONSTRAINT pk_kilometrage PRIMARY KEY ( id )
 );

CREATE  TABLE assurance ( 
	id                   integer DEFAULT nextval('assurance_id_seq'::regclass) NOT NULL  ,
	avion_id             integer    ,
	date_expiration      date    ,
	montant              numeric(11,2)    ,
	avion_id          bigint    ,
	CONSTRAINT pk_assurance PRIMARY KEY ( id )
 );

CREATE  TABLE assurance_paye ( 
	assurance_id         integer    ,
	montant_paye         numeric(11,2)    ,
	date_payement        date    
 );

CREATE  TABLE avion_assurances ( 
	avion_id             bigint  NOT NULL  ,
	assurances_id        bigint  NOT NULL  ,
	CONSTRAINT avion_assurances_pkey PRIMARY KEY ( avion_id, assurances_id ),
	CONSTRAINT uk_fobuwga2mjiots5m8pia9dxk4 UNIQUE ( assurances_id ) 
 );

CREATE  TABLE avion_kilometrages ( 
	avion_id             bigint  NOT NULL  ,
	kilometrages_id      bigint  NOT NULL  ,
	CONSTRAINT avion_kilometrages_pkey PRIMARY KEY ( avion_id, kilometrages_id ),
	CONSTRAINT uk_oqy1fi35b7vat2l184psbuqi2 UNIQUE ( kilometrages_id ) 
 );

ALTER TABLE assurance ADD CONSTRAINT fk_assurance_avion FOREIGN KEY ( avion_id ) REFERENCES avion( id );

ALTER TABLE assurance ADD CONSTRAINT fkgu8xd2fv0mgm4dyfy7nfw3ya0 FOREIGN KEY ( avion_id ) REFERENCES avion( id );

ALTER TABLE assurance_paye ADD CONSTRAINT fk_assurance_paye_assurance FOREIGN KEY ( assurance_id ) REFERENCES assurance( id );

ALTER TABLE avion ADD CONSTRAINT fk_modele_avion FOREIGN KEY ( modele_id ) REFERENCES modele( id );

ALTER TABLE avion_assurances ADD CONSTRAINT fkrqogcvn0mpik57vnok1w3t3qo FOREIGN KEY ( assurances_id ) REFERENCES assurance( id );

ALTER TABLE avion_assurances ADD CONSTRAINT fkovxjwrns6r6r1h7cnx2ivc896 FOREIGN KEY ( avion_id ) REFERENCES avion( id );

ALTER TABLE avion_kilometrages ADD CONSTRAINT fkk5rbitebp35sdvb70lej66bwd FOREIGN KEY ( avion_id ) REFERENCES avion( id );

ALTER TABLE avion_kilometrages ADD CONSTRAINT fko2qnnx9np4oqaol9off032d3q FOREIGN KEY ( kilometrages_id ) REFERENCES kilometrage( id );

ALTER TABLE entretien ADD CONSTRAINT fk_entretien_avion FOREIGN KEY ( avion_id ) REFERENCES avion( id );

ALTER TABLE entretien ADD CONSTRAINT fk_entretien_type_entretien FOREIGN KEY ( type_entretien_id ) REFERENCES type_entretien( id );

ALTER TABLE kilometrage ADD CONSTRAINT fk_kilometrage_avion FOREIGN KEY ( avion_id ) REFERENCES avion( id );

ALTER TABLE modele ADD CONSTRAINT fk_marque_modele FOREIGN KEY ( marque_id ) REFERENCES marque( id );

CREATE VIEW v_avion AS  SELECT v.id,
    v.modele_id,
    v.matricule,
    v.image,
    v.marque_id,
    v.nom,
    m.nom AS marque
   FROM (v_avion_modele v
     JOIN marque m ON ((v.marque_id = m.id)));

CREATE VIEW v_avion_modele AS  SELECT v.id,
    v.modele_id,
    v.matricule,
    v.image,
    m.marque_id,
    m.nom
   FROM (avion v
     JOIN modele m ON ((v.modele_id = m.id)));

INSERT INTO "admin"( id, email, mdp, nom ) VALUES ( 1, 'admin1@gmail.com', 'mdp1', 'rakoto');
INSERT INTO "admin"( id, email, mdp, nom ) VALUES ( 2, 'admin2@gmail.com', 'mdp2', 'rabe');
INSERT INTO "admin"( id, email, mdp, nom ) VALUES ( 3, 'admin3@gmail.com', 'mdp3', 'randria');
INSERT INTO "admin"( id, email, mdp, nom ) VALUES ( 4, 'admin4@gmail.com', 'mdp4', 'rainisoa');
INSERT INTO marque( id, nom ) VALUES ( 1, 'Boeing');
INSERT INTO marque( id, nom ) VALUES ( 2, 'Airbus');
INSERT INTO modele( id, marque_id, nom ) VALUES ( 1, 1, '747');
INSERT INTO modele( id, marque_id, nom ) VALUES ( 2, 1, '777');
INSERT INTO modele( id, marque_id, nom ) VALUES ( 3, 1, '777X');
INSERT INTO modele( id, marque_id, nom ) VALUES ( 4, 2, 'A320');
INSERT INTO modele( id, marque_id, nom ) VALUES ( 5, 2, 'A330');
INSERT INTO avion( id, modele_id, matricule, image ) VALUES ( 1, 1, '1477 TAB', null);
INSERT INTO avion( id, modele_id, matricule, image ) VALUES ( 2, 1, '4451 TBT', null);
INSERT INTO avion( id, modele_id, matricule, image ) VALUES ( 3, 1, '2491 FE', null);
INSERT INTO avion( id, modele_id, matricule, image ) VALUES ( 4, 1, '6401 TBK', null);
INSERT INTO avion( id, modele_id, matricule, image ) VALUES ( 5, 1, '5481 ME', null);
INSERT INTO avion( id, modele_id, matricule, image ) VALUES ( 6, 2, '1471 TBJ', null);
INSERT INTO avion( id, modele_id, matricule, image ) VALUES ( 7, 2, '1231 TAK', null);
INSERT INTO avion( id, modele_id, matricule, image ) VALUES ( 8, 2, '1961 TBM', null);
INSERT INTO avion( id, modele_id, matricule, image ) VALUES ( 9, 2, '1771 AG', null);
INSERT INTO avion( id, modele_id, matricule, image ) VALUES ( 10, 2, '9951 TAE', null);
INSERT INTO kilometrage( id, avion_id, "date", kilometrage ) VALUES ( 1, 1, '2022-12-10', 540000);
INSERT INTO kilometrage( id, avion_id, "date", kilometrage ) VALUES ( 2, 2, '2022-12-12', 590000);
INSERT INTO kilometrage( id, avion_id, "date", kilometrage ) VALUES ( 3, 3, '2022-12-13', 592800);
INSERT INTO kilometrage( id, avion_id, "date", kilometrage ) VALUES ( 4, 4, '2022-12-12', 687000);
INSERT INTO kilometrage( id, avion_id, "date", kilometrage ) VALUES ( 5, 5, '2022-12-01', 780000);
INSERT INTO kilometrage( id, avion_id, "date", kilometrage ) VALUES ( 6, 6, '2022-11-13', 486000);
INSERT INTO kilometrage( id, avion_id, "date", kilometrage ) VALUES ( 7, 7, '2022-12-12', 6700);
INSERT INTO kilometrage( id, avion_id, "date", kilometrage ) VALUES ( 8, 8, '2022-12-10', 10700);
INSERT INTO kilometrage( id, avion_id, "date", kilometrage ) VALUES ( 9, 9, '2022-11-05', 45689);
INSERT INTO kilometrage( id, avion_id, "date", kilometrage ) VALUES ( 10, 10, '2022-12-12', 879526);
INSERT INTO kilometrage( id, avion_id, "date", kilometrage ) VALUES ( 11, 1, '2022-12-12', 10400);
INSERT INTO kilometrage( id, avion_id, "date", kilometrage ) VALUES ( 12, 2, '2022-12-18', 627000);
INSERT INTO assurance( id, avion_id, date_expiration, montant, avion_id ) VALUES ( 1, 1, '2023-01-01', 21500000, null);
INSERT INTO assurance( id, avion_id, date_expiration, montant, avion_id ) VALUES ( 2, 2, '2023-03-01', 21500000, null);
INSERT INTO assurance( id, avion_id, date_expiration, montant, avion_id ) VALUES ( 3, 3, '2023-02-01', 21500000, null);
INSERT INTO assurance( id, avion_id, date_expiration, montant, avion_id ) VALUES ( 4, 4, '2023-01-01', 21500000, null);
INSERT INTO assurance( id, avion_id, date_expiration, montant, avion_id ) VALUES ( 5, 5, '2022-12-01', 21500000, null);
INSERT INTO assurance( id, avion_id, date_expiration, montant, avion_id ) VALUES ( 6, 6, '2023-03-01', 21500000, null);
INSERT INTO assurance( id, avion_id, date_expiration, montant, avion_id ) VALUES ( 7, 7, '2023-04-01', 21500000, null);
INSERT INTO assurance( id, avion_id, date_expiration, montant, avion_id ) VALUES ( 8, 8, '2023-01-15', 21500000, null);
INSERT INTO assurance( id, avion_id, date_expiration, montant, avion_id ) VALUES ( 9, 9, '2023-05-01', 21500000, null);
INSERT INTO assurance( id, avion_id, date_expiration, montant, avion_id ) VALUES ( 10, 10, '2023-01-15', 21500000, null);
