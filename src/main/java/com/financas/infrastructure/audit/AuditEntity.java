package com.financas.infrastructure.audit;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name= "revinfo_custom")
@RevisionEntity(AuditListener.class)
public class AuditEntity extends DefaultRevisionEntity{

	private static final long serialVersionUID = 1L;
	
	public String usuario;
}
