DROP DATABASE IF EXISTS netgest;
CREATE DATABASE netgest;
USE netgest;

CREATE TABLE ville(
    id INT NOT NULL PRIMARY KEY auto_increment,
    codepostal CHAR(5) NOT NULL,
    ville VARCHAR(100) NOT NULL
)Engine=InnoDB;

INSERT INTO ville(id, codepostal, ville)
VALUES
    (1, '33600', 'PESSAC'),
    (2, '33700', 'MERIGNAC'),
    (3, '33000', 'BORDEAUX'),
    (4, '06100', 'NICE'),
    (5, '64510', 'PAU'),
    (6, '33115', 'PYLA / MER'),
    (7, '47210', 'SAINT CIER / GIRONDE'),
    (8, '69001', 'LYON CEDEX 1'),
    (9, '84110', 'VAISON LA ROMAINE'),
    (10, '17154', 'LA ROCHELLE');

CREATE TABLE client(
    id INT NOT NULL PRIMARY KEY auto_increment,
    nom VARCHAR(50) NOT NULL,
    adresse1 VARCHAR(250) DEFAULT NULL,
    adresse2 VARCHAR(250) DEFAULT NULL,
    idcpville INT DEFAULT NULL,
    CONSTRAINT FK_CLI_VILLE FOREIGN KEY (idcpville) REFERENCES ville(id)
)Engine=InnoDB;

INSERT INTO client(id, nom, adresse1, adresse2, idcpville)
VALUES (1, 'AVALONE', '152 Avenue Jean-Jaurès', null, 1),
       (2, 'CGI', null, null, null),
       (3, 'EPSI', null, null, null),
       (4, 'CAP GEMINI', null, null, null),
       (5, 'EDF', null, null, null),
       (6, 'ORANGE', null, null, null),
       (7, 'SFR', null, null, null);

CREATE TABLE fonction(
    id INT NOT NULL PRIMARY KEY auto_increment,
    libelle VARCHAR(50) NOT NULL
)Engine=InnoDB;

INSERT INTO fonction(id, libelle)
VALUES (1, 'Gérant'),
       (2, 'Directeur'),
       (3, 'Technicien'),
       (4, 'Commercial'),
       (5, 'Intervenant'),
       (6, 'Consultant');

CREATE TABLE personne(
    id INT NOT NULL PRIMARY KEY auto_increment,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    telephone VARCHAR(14) DEFAULT NULL,
    email VARCHAR(100) NOT NULL
)Engine=InnoDB;

INSERT INTO personne(id, nom, prenom, telephone, email)
VALUES (1, 'GILLET', 'Michel', '06.03.45.71.18', 'michel@avalone-fr.com'),
       (2, 'SOUMAILLES', 'Lucas', null, 'lucas33@cgi-group.com'),
       (3, 'LEMONNIER', 'Marius', null, 'marius.lemonnier@campus-cd.com'),
       (4, 'CHESNEAU', 'Sébastien', null, 'sebkeyoc@cap-gemini.fr'),
       (5, 'GONZALES', 'Benjamin', null, 'benapparte@edf-for-fun.fr');

CREATE TABLE appartient(
    id INT NOT NULL PRIMARY KEY auto_increment,
    idpersonne INT NOT NULL,
    idfonction INT NOT NULL,
    idclient INT NOT NULL,
    CONSTRAINT FK_APP_CLI FOREIGN KEY (idclient) REFERENCES client(id),
    CONSTRAINT FK_APP_FCT FOREIGN KEY (idfonction) REFERENCES fonction(id),
    CONSTRAINT FK_APP_PRS FOREIGN KEY (idpersonne) REFERENCES personne(id)
)Engine=InnoDB;

INSERT INTO appartient(idpersonne, idfonction, idclient)
VALUES (1, 1, 1),
       (2, 3, 2),
       (3, 3, 3),
       (4, 4, 4),
       (5, 6, 6),
       (1, 5, 2);

CREATE TABLE typemateriel(
    id INT NOT NULL PRIMARY KEY auto_increment,
    libelle VARCHAR(50) NOT NULL
)Engine=InnoDB;

INSERT INTO typemateriel(id, libelle)
VALUES (1, 'Imprimante'),
       (2, 'Switch'),
       (3, 'Routeur'),
       (4, 'Serveur'),
       (5, 'Ordinateur');


CREATE TABLE materiel(
    id INT NOT NULL PRIMARY KEY auto_increment,
    libelle VARCHAR(100) NOT NULL,
    numserie VARCHAR(30) NOT NULL,
    idclient INT NOT NULL,
    idtype INT NOT NULL,
    CONSTRAINT FK_MAT_CLI FOREIGN KEY (idclient) REFERENCES client(id),
    CONSTRAINT FK_MAT_TYPE FOREIGN KEY (idtype) REFERENCES typemateriel(id)
)Engine=InnoDB;

INSERT INTO materiel(id, libelle, numserie, idclient, idtype)
VALUES (1, 'Dell 1520', '011201252012', 1, 1),
       (2, 'NETGEAR DS1008', '053518613548', 1, 2),
       (3, 'NETGEAR DS1005', '35s4d54s684f', 1, 2),
       (4, 'TP-LINK GS108', 'sdfsdf435438', 1, 3),
       (5, 'PASSERELLE LINUX', 'fhh64zeer64fg', 1, 4),
       (6, 'DEV NICOLAS', '6784f8dgd38', 1, 4);

CREATE TABLE typeif(
    id INT NOT NULL PRIMARY KEY auto_increment,
    libelle VARCHAR(10) NOT NULL
)Engine=InnoDB;

INSERT INTO typeif(id, libelle)
VALUES (1, 'LAN'),
       (2, 'WAN'),
       (3, 'BLUETOOTH'),
       (4, 'WLAN');

CREATE TABLE typeaffectation(
    id INT NOT NULL PRIMARY KEY auto_increment,
    libelle VARCHAR(10) NOT NULL
)Engine=InnoDB;

INSERT INTO typeaffectation(id, libelle)
VALUES (1, 'DHCP'),
       (2, 'STATIC');

CREATE TABLE interface(
    id INT NOT NULL PRIMARY KEY auto_increment,
    nom VARCHAR(10) NOT NULL,
    mac VARCHAR(14) DEFAULT '00:00:00:00:00',
    idtype INT NOT NULL,
    idmateriel INT NOT NULL,
    CONSTRAINT FK_TYPEIF FOREIGN KEY (idtype) REFERENCES typeif(id),
    CONSTRAINT FK_IFMATOS FOREIGN KEY (idmateriel) REFERENCES materiel(id)
)Engine=InnoDB;

INSERT INTO interface(id, nom, mac, idtype, idmateriel)
VALUES (1, 'eth0', '01:02:02:02:02', 1, 5),
       (2, 'eth1', '01:02:02:02:03', 1, 5),
       (3, 'wl0', '18:1F:01:01:01', 4, 4),
       (4, 'eth0', '18:1F:01:01:02', 2, 4),
       (5, 'eth0', 'E6:1F:01:01:01', 1, 3),
       (6, 'eth1', 'E6:1F:01:01:02', 1, 3),
       (7, 'eth2', 'E6:1F:01:01:03', 1, 3),
       (8, 'eth3', 'E6:1F:01:01:04', 1, 3);

CREATE TABLE adresseip(
    id INT NOT NULL PRIMARY KEY auto_increment,
    ipV4 VARCHAR(15) NOT NULL,
    ipV6 VARCHAR(100) DEFAULT NULL,
    masque VARCHAR(15) NOT NULL DEFAULT '255.255.255.0',
    idinterface INT NOT NULL,
    idtypeaff INT NOT NULL,
    CONSTRAINT FK_AD_IF FOREIGN KEY (idinterface) REFERENCES interface(id),
    CONSTRAINT FK_AD_TYPAF FOREIGN KEY (idtypeaff) REFERENCES typeaffectation(id)
)Engine=InnoDB;

INSERT INTO adresseip(id, ipV4, ipV6, masque, idinterface, idtypeaff)
VALUES (1, '192.168.254.250', null, '255.255.255.0', 1, 1),
       (2, '17.10.0.1', null, '255.255.255.240', 2, 1),
       (3, '192.168.251.254', null, '255.255.255.0', 3, 1),
       (4, '192.168.254.251', null, '255.255.255.240', 4, 1),
       (5, '192.168.254.10', null, '255.255.255.0', 5, 2),
       (6, '192.168.254.11', null, '255.255.255.0', 6, 2),
       (7, '192.168.254.12', null, '255.255.255.0', 7, 2),
       (8, '192.168.254.13', null, '255.255.255.0', 8, 2);