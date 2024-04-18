package com.petry.pdv.pedido.controller;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.pedido.entity.Pedido;
import com.petry.pdv.pedido.entity.PedidoInseridoEvent;
import com.petry.pdv.pedido.service.PedidoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/pedido")
public class PedidoController {

	private Map<Long, FluxSink<Pedido>> lojasConectadas = new ConcurrentHashMap<>();

	@Autowired
	private PedidoService service;
	
	@PutMapping
	public ResponseEntity confirmarPedido(@RequestBody Pedido pedido) {
		
		return service.confirmPedido(pedido);
		
	}
	@PostMapping
	public ResponseEntity insertPedidoLoja(@RequestBody Pedido pedido) {
		
		return service.savepedidoLoja(pedido);
		
	}
	@DeleteMapping
	public ResponseEntity cancelarPedidoLoja(@RequestBody Pedido pedido) {
		return service.inativarPedido(pedido);

	}
//	@PostMapping
//	public ResponseEntity insertPedidoLoja(@RequestBody Pedido pedido) {
//		pedido.setStatus(2);
//		pedido.setDtpedido(new Date());
//		return service.savepedidoLoja(pedido);
//		
//	}
	@GetMapping
	public ResponseEntity getPedidoByLoja(@RequestParam("size") Integer size,
										   @RequestParam("page") Integer page,
										   @RequestParam("codloja") Integer codloja,
										   @RequestParam("status") Integer status

										   ){
		
		return service.getPedidos(page, size, codloja, status);	
	}
	
	
//	 @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//	    public Flux<String> streamEvents() {
//		  System.out.println("Chamando SSE...");
//	        return Flux.interval(Duration.ofSeconds(1))
//	                .map(sequence -> "data:" + LocalTime.now() + "\n\n");
//	    }
	 @GetMapping(value = "/sse/{codloja}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	 public Flux<Pedido> streamEvents(@PathVariable(name = "codloja" ) Long codloja) {
	        return Flux.create(sink -> {
	            // Armazenar referência ao FluxSink associado ao cliente
	        	
	            lojasConectadas.put(codloja, sink);
	           System.out.println(lojasConectadas.toString());
	            // Limpeza quando a conexão é fechada
	            sink.onDispose(() -> {
	                lojasConectadas.remove(codloja);
	            });
	        });
	    }
	 
	 @EventListener
	    public void onPedidoInserido(PedidoInseridoEvent event) {
	        Pedido pedido = event.getPedido();
	        notifyClient(pedido);
	    }
	 
	 private void notifyClient(Pedido pedido) {
		    FluxSink<Pedido> sink = lojasConectadas.get(pedido.getId().getIdlojaPedido());
		    System.out.println(lojasConectadas.toString());
		    if (sink != null) {
		        sink.next(pedido);
		    }
		}
	
}
