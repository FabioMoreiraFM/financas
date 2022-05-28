package com.financas.domain.listener.event;

import org.springframework.context.ApplicationEvent;

public class AgendaEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public AgendaEvent(Object source) {
		super(source);
	}

}
