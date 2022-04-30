CREATE TABLE regras (
	idRegras int(11) NOT NULL auto_increment,
	qtdMaxEnvioAgendamento int(4) not null default 3,
	fatorDespesaReceitaEnvioEmail double not null default 0.9,
	enviarEmailDespesasAtrasadas tinyint(1) not null default true,
	PRIMARY KEY (idRegras) 	
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table tipo_despesa (
	idTipoDespesa int(11) not null auto_increment,
	descricao varchar(255) not null,
	primary key (idTipoDespesa)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table tipo_receita (
	idTipoReceita int(11) not null auto_increment,
	descricao varchar(255) not null,
	primary key (idTipoReceita)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table usuario (
	idUsuario int(11) not null auto_increment,
	nome varchar(255) not null, 
	email varchar(255) not null,
	senha varchar(255) not null,
	cep varchar(12),
	logradouro varchar(255),
	bairro varchar(255),
	cidade varchar(255),
	uf varchar(2),
	celular varchar(13) not null,
	dataCadastro datetime,
	primary key (idUsuario)
) ENGINE=InnoDB DEFAULT CHARSET=latin1; 

CREATE TABLE agenda (
	idAgenda int(11) not null auto_increment,
	titulo varchar(255) not null,
	corpoEmail varchar(255) not null,
	email varchar(255) not null,
	enviosRealizados int(4) not null,
	dtPrimeiroEnvio date,
	dtUltimoEnvio date,
	envioFinalizado tinyint(4) not null,
	primary key (idAgenda)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table terceiro (
	idTerceiro int(11) not null auto_increment,
	descricao varchar(255) not null,
	documento varchar(20) not null,
	tipoTerceiro varchar(255) not null,
	primary key (idTerceiro)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table receita (
	idReceita int(11) not null auto_increment,
	descricao varchar(255) not null,
	valor decimal(10,2) not null,
	fonte int(11) not null,
	dtInicio date not null,
	dtFim date,
	idTipoReceita int(11) not null,
	idUsuario int(11) not null,
	primary key (idReceita),
	foreign key (fonte) references terceiro(idTerceiro),
	foreign key (idTipoReceita) references tipo_receita(idTipoReceita),
	foreign key (idUsuario) references usuario(idUsuario)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table despesa (
	idDespesa int(11) not null auto_increment,
	descricao varchar(255) not null,
	valorTotal decimal(10,2) not null,
	valorParcela decimal(10,2) not null,
	totalParcelas int(11) not null,
	credor int(11) not null,
	mesAnoInicioPgto date not null,
	dtQuitacao date,
	diaVencimentoParcela int(4) not null,
	idTipoDespesa int(11) not null,
	idUsuario int(11) not null,
	primary key (idDespesa),
	foreign key (credor) references terceiro(idTerceiro),
	foreign key (idTipoDespesa) references tipo_despesa(idTipoDespesa),
	foreign key (idUsuario) references usuario(idUsuario)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table parcela_despesa (
	idParcelaDespesa int(11) not null auto_increment,
	valor decimal(10,2) not null,
	nParcela int(11) not null,
	dtVencimento date not null,
	dtPagamento date,
	idDespesa int(11) not null,
	idAgenda int(11),
	primary key (idParcelaDespesa),
	foreign key (idDespesa) references despesa(idDespesa),
	foreign key (idAgenda) references agenda(idAgenda)
) ENGINE=InnoDB DEFAULT CHARSET=latin1; 

-- financas.revinfo_custom definition
 
CREATE TABLE `revinfo_custom` (
  `id` int(11) NOT null AUTO_INCREMENT, 
  `timestamp` bigint(20) NOT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 

-- financas.hibernate_sequence definition

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO financas.hibernate_sequence (next_val) VALUES(1);

-- financas.despesa_aud definition

CREATE TABLE `despesa_aud` (
  `idDespesa` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `diaVencimentoParcela` int(11) DEFAULT NULL,
  `dtQuitacao` date DEFAULT NULL,
  `mesAnoInicioPgto` date DEFAULT NULL,
  `totalParcelas` int(11) DEFAULT NULL,
  `valorParcela` decimal(19,2) DEFAULT NULL, 
  `valorTotal` decimal(19,2) DEFAULT NULL,
  `credor` bigint(20) DEFAULT NULL,
  `idTipoDespesa` bigint(20) DEFAULT NULL,
  `idUsuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idDespesa`,`REV`),
  KEY `FK8dgxo0q42lu9cqqyc9b72kbp2` (`REV`),
  CONSTRAINT `FK8dgxo0q42lu9cqqyc9b72kbp2` FOREIGN KEY (`REV`) REFERENCES `revinfo_custom` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- financas.parcela_despesa_aud definition

CREATE TABLE `parcela_despesa_aud` (
  `idParcelaDespesa` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `dtPagamento` date DEFAULT NULL,
  `dtVencimento` date DEFAULT NULL,
  `nParcela` int(11) DEFAULT NULL,
  `valor` decimal(19,2) DEFAULT NULL,
  `idDespesa` bigint(20) DEFAULT NULL,
  `idAgenda` bigint(20),
  PRIMARY KEY (`idParcelaDespesa`,`REV`),
  KEY `FKd7v609ks3k6serqelwe7tp0c6` (`REV`),
  CONSTRAINT `FKd7v609ks3k6serqelwe7tp0c6` FOREIGN KEY (`REV`) REFERENCES `revinfo_custom` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- financas.receita_aud definition

CREATE TABLE `receita_aud` (
  `idReceita` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `dtFim` date DEFAULT NULL,
  `dtInicio` date DEFAULT NULL,
  `valor` decimal(19,2) DEFAULT NULL,
  `fonte` bigint(20) DEFAULT NULL,
  `idTipoReceita` bigint(20) DEFAULT NULL,
  `idUsuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idReceita`,`REV`),
  KEY `FKdj06egaefqxmen6khgftefb9` (`REV`),
  CONSTRAINT `FKdj06egaefqxmen6khgftefb9` FOREIGN KEY (`REV`) REFERENCES `revinfo_custom` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- financas.regras_aud definition

CREATE TABLE `regras_aud` (
  `idRegras` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `enviarEmailDespesasAtrasadas` bit(1) DEFAULT NULL,
  `fatorDespesaReceitaEnvioEmail` double DEFAULT NULL,
  `qtdMaxEnvioAgendamento` int(11) DEFAULT NULL,
  PRIMARY KEY (`idRegras`,`REV`),
  KEY `FK2hyjey7ylrqumoxi57ck5agq6` (`REV`),
  CONSTRAINT `FK2hyjey7ylrqumoxi57ck5agq6` FOREIGN KEY (`REV`) REFERENCES `revinfo_custom` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- financas.terceiro_aud definition

CREATE TABLE `terceiro_aud` (
  `idTerceiro` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `documento` varchar(255) DEFAULT NULL,
  `tipoTerceiro` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTerceiro`,`REV`),
  KEY `FK2eg9rc5w3hybv0ayis7xfu8k8` (`REV`),
  CONSTRAINT `FK2eg9rc5w3hybv0ayis7xfu8k8` FOREIGN KEY (`REV`) REFERENCES `revinfo_custom` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- financas.tipo_despesa_aud definition

CREATE TABLE `tipo_despesa_aud` (
  `idTipoDespesa` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTipoDespesa`,`REV`),
  KEY `FK6t7ywgiyfo3k8kkhesn5hbgdv` (`REV`),
  CONSTRAINT `FK6t7ywgiyfo3k8kkhesn5hbgdv` FOREIGN KEY (`REV`) REFERENCES `revinfo_custom` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- financas.usuario_aud definition

CREATE TABLE `usuario_aud` (
  `idUsuario` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `celular` varchar(255) DEFAULT NULL,
  `dataCadastro` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `uf` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`,`REV`),
  KEY `FKccqpbcawc1yublnm3f1c0q8ie` (`REV`),
  CONSTRAINT `FKccqpbcawc1yublnm3f1c0q8ie` FOREIGN KEY (`REV`) REFERENCES `revinfo_custom` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- financas.tipo_receita_aud definition

CREATE TABLE `tipo_receita_aud` (
  `idTipoReceita` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTipoReceita`,`REV`),
  KEY `FK1ri0tlqnadvs0n8d9db38q1s4` (`REV`),
  CONSTRAINT `FK1ri0tlqnadvs0n8d9db38q1s4` FOREIGN KEY (`REV`) REFERENCES `revinfo_custom` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- financas.agenda_aud definition

CREATE TABLE agenda_aud (
	idAgenda bigint(20) NOT NULL,
	REV int(11) NOT NULL,
	REVTYPE tinyint(4) DEFAULT NULL,
	titulo varchar(255) not null,
	corpoEmail varchar(255) not null,
	email varchar(255) not null,
	enviosRealizados int(4) not null,
	dtPrimeiroEnvio date,
	dtUltimoEnvio date,
	envioFinalizado tinyint(4) not null,
	primary key (idAgenda, REV),
	KEY `FK8davn1q42lu9cqqyc9b72kbp2` (`REV`),
  	CONSTRAINT `FK8davn1q42lu9cqqyc9b72kbp2` FOREIGN KEY (`REV`) REFERENCES `revinfo_custom` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
