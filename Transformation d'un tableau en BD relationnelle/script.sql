--DELOIN Gaspar 11806858 
--NIAMKE Fian 11715528
drop table if exists Departement cascade;
create table Departement(
    idDep integer not null,
    nomDep varchar(80) not null,
    primary key(idDep)
);

drop table if exists Circonscription cascade;
create table Circonscription(
    idCir integer not null,
    idDep integer not null,
    nomCirc varchar(80) not null,
    primary key(idCir,idDep),
    constraint idDep foreign key (idDep) references Departement (idDep)
);

drop table if exists Commune cascade;
create table Commune(
    idCom integer not null,
    idDep integer not null,
    nomCom varchar(80) not null,
    primary key(idCom,idDep),
    constraint idDep foreign key (idDep) references Departement (idDep)
);

drop table if exists Bureau_de_Vote cascade;
create table Bureau_de_Vote(
    idCom integer not null,
    idDep integer not null,
    idBur integer not null,
    nbIns integer not null,
    nbAbst integer not null,
    nbBlanc integer not null,
    nbNul integer not null,
    adresse varchar(80) not null,
    primary key(idBur,idCom,idDep),
    constraint BVote foreign key (idCom,idDep) references Commune (idCom,idDep)
);

drop table if exists Appartient cascade;
create table Appartient(
    idCom integer not null,
    idDep integer not null,
    idBur integer not null,
    idCir integer not null,
    constraint idBur foreign key (idBur,idCom,idDep) references Bureau_de_Vote (idBur,idCom,idDep),
    constraint idCir foreign key (idCir,idDep) references Circonscription (idCir,idDep)
);
drop table if exists Candidat cascade;
create table Candidat(
    numPanneau integer not null,
    Nom varchar(80) not null,
    Prénom varchar(80) not null,
    Sexe varchar(80) not null,
    primary key(numPanneau)
 );

drop table if exists Resultat_par_Bureau cascade;
create table Resultat_par_Bureau(
    idCom integer not null,
    idDep integer not null,
    idBur integer not null,
    numPanneau integer not null,
    nbVote integer not null,
    constraint idBur foreign key (idBur,idCom,idDep) references Bureau_de_Vote (idBur,idCom,idDep),
    constraint numPanneau foreign key (numPanneau) references Candidat (numPanneau)
 );

insert into Departement 
select distinct "Code du département", "Département"
from "election-csv" ;

insert into Circonscription  
select distinct "Code de la circonscription", "Code du département", "Circonscription"
from "election-csv" ;

insert into Commune  
select distinct "Code de la commune", "Code du département", "Commune"
from "election-csv" ;

insert into bureau_de_vote  
select distinct "Code de la commune", "Code du département", "Bureau de vote","Inscrits","Abstentions","Blancs","Nuls","Adresse"
from "election-csv" ;

insert into candidat  
select distinct "N°Panneau","Nom","Prénom","Sexe"
from "election-csv" ;

insert into appartient 
select distinct "Code de la commune","Code du département","Bureau de vote","Code de la circonscription"
from "election-csv";

insert into resultat_par_bureau 
select "Code de la commune","Code du département","Bureau de vote","N°Panneau","Voix"
from "election-csv";

drop function if exists best_par_candidat;
create function best_par_candidat(numPan integer)
  returns table (prct_voix float,idCom integer,nomCom varchar(80), classement bigint, nom varchar(80), prénom varchar(80))
as
$$
select nb::float/nbVoixExprimes::float*100 as prct_voix, idCom, nomCom, classement, nom, prénom 
from 
	(
	select nb,idDep,idCom, nomCom,numpanneau ,rank() over (partition by idDep,idCom order by nb desc) as classement, cdd.nom as nom, cdd.prénom as prénom 
	from
		(
		select sum(nbVote) as nb, idDep, idCom, c.nomcom as nomCom, numpanneau 
		from resultat_par_bureau rpb 
		natural join bureau_de_vote bdv 
		natural join Commune c
		group by (idDep,idCom, c.nomcom,numPanneau) having sum(nbIns - nbBlanc - nbNul - nbAbst)>3000
		) as a
	natural join candidat cdd
	) as b
natural join 
	(
	select sum(nbIns - nbBlanc - nbNul - nbAbst) as nbVoixExprimes, idDep,idCom
	from bureau_de_vote bdv2
	group by(idDep,idCom) having sum(nbIns - nbBlanc - nbNul - nbAbst)>3000
	) as c
where numpanneau = numPan
order by prct_voix desc, nbVoixExprimes desc
limit 10; 
$$
language sql;

select * from best_par_candidat(3);

