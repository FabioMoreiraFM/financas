create table grupo (
	id int(11) not null auto_increment,
	nome varchar(60) not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8;

create table grupo_permissao (
	grupo_id int(11) not null,
	permissao_id int(11) not null,
	
	primary key (grupo_id, permissao_id)
) engine=InnoDB default charset=utf8;

create table permissao (
	id int(11) not null auto_increment,
	descricao varchar(60) not null,
	nome varchar(100) not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8;

create table usuario_grupo (
	usuario_id int(11) not null,
	grupo_id int(11) not null,
	
	primary key (usuario_id, grupo_id)
) engine=InnoDB default charset=utf8;


alter table grupo_permissao add constraint fk_grupo_permissao_permissao
foreign key (permissao_id) references permissao (id);

alter table grupo_permissao add constraint fk_grupo_permissao_grupo
foreign key (grupo_id) references grupo (id);

alter table usuario_grupo add constraint fk_usuario_grupo_grupo
foreign key (grupo_id) references grupo (id);

alter table usuario_grupo add constraint fk_usuario_grupo_usuario
foreign key (usuario_id) references usuario (idUsuario);

create table oauth_client_details (
  client_id varchar(255),
  resource_ids varchar(256),
  client_secret varchar(256),
  scope varchar(256),
  authorized_grant_types varchar(256),
  web_server_redirect_uri varchar(256),
  authorities varchar(256),
  access_token_validity integer,
  refresh_token_validity integer,
  additional_information varchar(4096),
  autoapprove varchar(256),
  
  primary key (client_id)
) engine=innodb default charset=utf8;
