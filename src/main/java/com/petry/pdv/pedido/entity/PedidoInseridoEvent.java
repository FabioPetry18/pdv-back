package com.petry.pdv.pedido.entity;

import org.springframework.context.ApplicationEvent;

public class PedidoInseridoEvent extends ApplicationEvent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Pedido pedido;

    public PedidoInseridoEvent(Object source, Pedido pedido) {
        super(source);
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }
}