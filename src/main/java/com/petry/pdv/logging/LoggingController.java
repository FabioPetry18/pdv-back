package com.petry.pdv.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.FpMsPdvApplication;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/log")
public class LoggingController {
    private static final Logger log = LoggerFactory.getLogger(FpMsPdvApplication.class);

	@GetMapping
	public void log() throws Exception {
		log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");
        throw new Exception("Erro teste");
		
	}
}
