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

create table auditoria (
	idAuditoria int(11) not null auto_increment,
	descricao varchar(400) not null,
	tipoOperacao varchar(50) not null,
	tabela varchar(50) not null,
	nomeUsuario varchar(255) not null,
	mapObjetoAntigo text,
	mapObjetoNovo text,
	primary key (idAuditoria)
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
	primary key (idParcelaDespesa),
	foreign key (idDespesa) references despesa(idDespesa)
) ENGINE=InnoDB DEFAULT CHARSET=latin1; 

CREATE TABLE agenda (
	idAgenda int(11) not null auto_increment,
	corpoEmail varchar(255) not null,
	email varchar(255) not null,
	enviosRealizados int(4) not null,
	dtPrimeiroEnvio date,
	dtUltimoEnvio date,
	idReceita int(11),
	idDespesa int(11),
	primary key (idAgenda),
	foreign key (idReceita) references receita(idReceita),
	foreign key (idDespesa) references despesa(idDespesa)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;