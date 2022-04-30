package com.financas.infrastructure.audit;

import org.hibernate.envers.RevisionListener;

public class AuditListener implements RevisionListener{

	@Override
	public void newRevision(Object revisionEntity) {
		AuditEntity revEntity = (AuditEntity) revisionEntity;  
		
		revEntity.setUsuario("fabio");
	}
	
}
