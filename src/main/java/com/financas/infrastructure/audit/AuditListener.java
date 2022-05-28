package com.financas.infrastructure.audit;

import java.util.Optional;

import org.hibernate.envers.RevisionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import com.financas.infrastructure.security.FinancasSecurity;

public class AuditListener implements RevisionListener{

	@Autowired
	private FinancasSecurity financasSecurity;
	
	private static String ROTINA_AUTOMATICA = "ROTINA AUTOMÁTICA";
	
	@Override
	public void newRevision(Object revisionEntity) {
		AuditEntity revEntity = (AuditEntity) revisionEntity;  
		
		Optional<Authentication> auth = Optional.ofNullable(financasSecurity.getAuthentication());
		revEntity.setUsuario(auth.isPresent() ? auth.get().getName() : ROTINA_AUTOMATICA);
	}
	
}
