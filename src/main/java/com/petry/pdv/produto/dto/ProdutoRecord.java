package com.petry.pdv.produto.dto;

import org.springframework.web.multipart.MultipartFile;

public record ProdutoRecord(ProdutoDTO dto, MultipartFile productFile) {

}
