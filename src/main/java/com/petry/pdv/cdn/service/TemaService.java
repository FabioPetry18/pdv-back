package com.petry.pdv.cdn.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.petry.pdv.cdn.dto.TemaDTO;

@Service
public class TemaService {
 
	 public static String gerarTemaCSS(TemaDTO tema) {
	        return """
	            @plugin "daisyui/theme" {
	              name: "%s";
	              default: false;
	              prefersdark: false;
	              color-scheme: "light";
	              --color-base-100: %s;
	              --color-base-200: %s;
	              --color-base-300: %s;
	              --color-primary: %s;
	              --color-secondary: %s;
	              --color-accent: %s;
	              --color-neutral: %s;
	              --radius-selector: 0.5rem;
	              --radius-field: 0.25rem;
	              --radius-box: 0.25rem;
	              --size-selector: 0.25rem;
	              --size-field: 0.25rem;
	              --border: 2px;
	              --depth: 0;
	              --noise: 1;
	            }
	        """.formatted(
	            tema.getName(),
	            tema.getColorBase100(),
	            tema.getColorBase200(),
	            tema.getColorBase300(),
	            tema.getColorPrimary(),
	            tema.getColorSecondary(),
	            tema.getColorAccent(),
	            tema.getColorNeutral()
	        );
	    }
	 public static String gerarTemaCSSStatic(TemaDTO tema) {
		    return """
		          @import "tailwindcss";
		@plugin "daisyui";
		@plugin "daisyui/theme" {
		  name: "mytheme";
		  default: true; /* set as default */
		  prefersdark: false; /* set as default dark mode (prefers-color-scheme:dark) */
		  color-scheme: light; /* color of browser-provided UI */

		  --color-base-100: oklch(98% 0.02 240);
		  --color-base-200: oklch(95% 0.03 240);
		  --color-base-300: oklch(92% 0.04 240);
		  --color-base-content: oklch(20% 0.05 240);
		  --color-primary: oklch(60% 0.3 30);
		  --color-primary-content: oklch(98% 0.01 240);
		  --color-secondary: oklch(70% 0.25 200);
		  --color-secondary-content: oklch(98% 0.01 200);
		  --color-accent: oklch(65% 0.25 160);
		  --color-accent-content: oklch(98% 0.01 160);
		  --color-neutral: oklch(50% 0.05 240);
		  --color-neutral-content: oklch(98% 0.01 240);
		  --color-info: oklch(70% 0.2 220);
		  --color-info-content: oklch(98% 0.01 220);
		  --color-success: oklch(65% 0.25 140);
		  --color-success-content: oklch(98% 0.01 140);
		  --color-warning: oklch(80% 0.25 80);
		  --color-warning-content: oklch(20% 0.05 80);
		  --color-error: oklch(65% 0.3 30);
		  --color-error-content: oklch(98% 0.01 30);

		  /* border radius */
		  --radius-selector: 1rem;
		  --radius-field: 0.25rem;
		  --radius-box: 0.5rem;

		  /* base sizes */
		  --size-selector: 0.25rem;
		  --size-field: 0.25rem;

		  /* border size */
		  --border: 1px;

		  /* effects */
		  --depth: 1;
		  --noise: 0;
		}
		    """;
		}
	 public static void salvarTemaNoDisco(String nomeArquivo, String conteudo) throws IOException {
		    Path path = Paths.get("temas-gerados", nomeArquivo);
		    Files.createDirectories(path.getParent());
		    Files.write(path, conteudo.getBytes(StandardCharsets.UTF_8));
		}
}
