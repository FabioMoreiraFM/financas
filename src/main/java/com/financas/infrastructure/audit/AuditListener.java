package com.financas.infrastructure.audit;

import org.hibernate.envers.RevisionListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.financas.infrastructure.security.FinancasSecurity;

public class AuditListener implements RevisionListener{

	@Autowired
	private FinancasSecurity financasSecurity;
	
	@Override
	public void newRevision(Object revisionEntity) {
		AuditEntity revEntity = (AuditEntity) revisionEntity;  
		
		revEntity.setUsuario(financasSecurity.getAuthentication().getName());
	}
	
}
