create table comprovante (
     idComprovante int(11) not null auto_increment,
     nome varchar(150) not null,
     descricao varchar(150),
     contentType varchar(80) not null,
     tamanho int not null,
     idParcelaDespesa int(11) not null,

     primary key (idComprovante),
     constraint fk_comprovante_parcela_despesa foreign key (idParcelaDespesa) references parcela_despesa (idParcelaDespesa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table comprovante_aud (
     idComprovante bigint(20) NOT NULL,
     nome varchar(150) DEFAULT NULL,
     REV int(11) NOT NULL,
     REVTYPE tinyint(4) DEFAULT NULL,
     descricao varchar(150) DEFAULT NULL,
     contentType varchar(80) DEFAULT NULL,
     tamanho int DEFAULT NULL,
     idParcelaDespesa int(11) DEFAULT NULL,
     PRIMARY KEY (idComprovante,REV),
     KEY `FKd7v609ksck6serqelwe7tp0c6` (`REV`),
     CONSTRAINT `FKd7v609ksck6serqelwe7tp0c6` FOREIGN KEY (`REV`) REFERENCES `revinfo_custom` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;