-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');

INSERT INTO users(username,password,enabled) VALUES ('owner2','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'owner2','owner');

INSERT INTO users(username,password,enabled) VALUES ('antcardia4', 'acd', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5, 'antcardia4', 'owner');

INSERT INTO users(username,password,enabled) VALUES ('alecamgal1', 'acg', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6, 'alecamgal1', 'owner');

INSERT INTO users(username,password,enabled) VALUES ('edupizlop', 'acg', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7, 'edupizlop', 'owner');

INSERT INTO users(username,password,enabled) VALUES ('pabmergom', 'pmg', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8, 'pabmergom', 'owner');

INSERT INTO users(username,password,enabled) VALUES ('juacamgar2', 'jcg', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9, 'juacamgar2', 'owner');

INSERT INTO vets(id, first_name,last_name) VALUES (1, 'James', 'Carter');
INSERT INTO vets(id, first_name,last_name) VALUES (2, 'Helen', 'Leary');
INSERT INTO vets(id, first_name,last_name) VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets(id, first_name,last_name) VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets(id, first_name,last_name) VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets(id, first_name,last_name) VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');
INSERT INTO types VALUES (7, 'turtle');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');
INSERT INTO owners VALUES (11, 'Guaje', 'Campos', '2335 Independence Lp.', 'Waunakei', '6085555434', 'owner2');
INSERT INTO owners VALUES (12, 'Antonio', 'Carretero', '123 Springfield St.', 'McFarland', '6085558673', 'antcardia4');
INSERT INTO owners VALUES (13, 'Alejandro', 'Campano', '224 Jhon Keneddy', 'Liberty City', '6054678643', 'alecamgal1');
INSERT INTO owners values (14,'Eduardo', 'Pizarro', '2 To use To use', 'Chipiona', '2222222222', 'edupizlop');
INSERT INTO owners values (15,'Pablo', 'Mera', '619 Speed St.', 'Los Angeles', '678346789', 'pabmergom');

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'Pelu', '2018-06-08', 1, 11);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (15, 'Mark', '2011-04-07', 2, 12);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (16, 'Mesi', '2017-05-02', 2, 13);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (17, 'Cristiano rounalduu SUUUWEY', '2012-02-02', 1, 14);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (18, 'Killo', '2015-02-07', 2, 15);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');

INSERT INTO facciones(id,name) VALUES(1,'Leal');
INSERT INTO facciones(id,name) VALUES(2,'Traidor');
INSERT INTO facciones(id,name) VALUES(3,'Mercader');

INSERT INTO dificultad(id,name) VALUES(1,'Facil');
INSERT INTO dificultad(id,name) VALUES(2,'Normal');
INSERT INTO dificultad(id,name) VALUES(3,'Dificil');

INSERT INTO partida(id,ronda,turno,num_jugadores,anfitrion,votos_favor_cesar,votos_contra_cesar,limite,faccion_ganadora_id,tiempo)
    VALUES(1,1,1,6,'Guaje',14,12,15,1,20);
INSERT INTO partida(id,ronda,turno,num_jugadores,anfitrion,votos_favor_cesar,votos_contra_cesar,limite,faccion_ganadora_id,tiempo)
    VALUES(2,1,1,6,'Jose',10,14,15,2,29);
INSERT INTO partida(id,ronda,turno,num_jugadores,anfitrion,votos_favor_cesar,votos_contra_cesar,limite,faccion_ganadora_id,tiempo)
    VALUES(3,4,1,8,'Juan',19,12,20,1,30);
INSERT INTO partida(id,ronda,turno,num_jugadores,anfitrion,votos_favor_cesar,votos_contra_cesar,limite,faccion_ganadora_id,tiempo)
    VALUES(4,1,1,6,'Jose',14,14,15,3,26);
INSERT INTO partida(id,ronda,turno,num_jugadores,anfitrion,votos_favor_cesar,votos_contra_cesar,limite,faccion_ganadora_id,tiempo)
    VALUES(5,2,1,5,'Pablo',8,10,15,2,26);
INSERT INTO partida(id,ronda,turno,num_jugadores,anfitrion,votos_favor_cesar,votos_contra_cesar,limite,faccion_ganadora_id,tiempo)
    VALUES(6,2,2,5,'Pablo',10,10,15,3,26);
INSERT INTO partida(id,ronda,turno,num_jugadores,anfitrion,votos_favor_cesar,votos_contra_cesar,limite,faccion_ganadora_id,tiempo)
    VALUES(7,2,3,5,'Pablo',11,10,15,1,26);

 INSERT INTO tipo(id, name)
    VALUES(2, 'Por ganar');
 INSERT INTO tipo(id, name)
    VALUES(1, 'Por jugar');

INSERT INTO tipo(id, name)
    VALUES(3, 'Victorias leal');

INSERT INTO tipo(id, name)
    VALUES(4, 'Victorias traidor');

INSERT INTO tipo(id, name)
    VALUES(5, 'Victorias mercader');


INSERT INTO logro(id, nombre, descripcion, tipo_id, limite, dificultad_id)
    VALUES(1, 'Maestro Pokemon', 'Obtén todos los pokemon', 1, 10, 3);

INSERT INTO logro(id, nombre, descripcion, tipo_id, limite, dificultad_id)
    VALUES(2, 'Maestro Fifa', 'Se mejor que guaje en fifa', 2, 10, 3);

INSERT INTO logro(id, nombre, descripcion, tipo_id, limite, dificultad_id)
    VALUES(3, 'Mbappe', 'Sigue los pasos de Mbappe', 4, 100, 3);


INSERT INTO rol(id,name)
    VALUES(1,'Cónsul'),(2,'Pretor'),(3,'Edil');


INSERT INTO users(username,password,enabled) VALUES ('Guaje', '1111', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (10, 'Guaje', 'jugador');

INSERT INTO users(username,password,enabled) VALUES ('Antaca', '1111', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (11, 'Antaca', 'jugador');

INSERT INTO jugador(id,first_name,last_name,username,rol_id,esta_en_partida)
    VALUES(1,'Juan Jesús','Campos','Guaje',Null,false),
    (2,'Antonio','Carretero','Antaca',Null,false);
    

INSERT INTO participacion(id, es_anfitrion,num_consul,votos_contra_cesar,votos_favor_cesar,votos_neutros,faccion_apoyada_id)
    VALUES(1,true,1,3,0,0,1),
    (2,false,2,2,1,0,2),
    (3,false,1,1,1,2,3),
    (4,false,1,0,2,2,3);

INSERT INTO partida_participaciones(partida_id,participaciones_id)
    VALUES(1,1),
    (2,3),
    (3,4),
    (2,2);
    
INSERT INTO jugador_participaciones(jugador_id,participaciones_id)
    VALUES(1,1),
    (1,3),
    (1,4),
    (2,2);

INSERT INTO partida_jugadores(partidas_id,jugadores_id) 
    VALUES(1,1),
    (2,1),
    (3,1),
    (2,2);


