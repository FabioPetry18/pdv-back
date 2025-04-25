package com.petry.pdv.utils.s3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {
	
 	@Autowired
    private S3Client s3Client;
 	
    @Value("${aws.bucket-name-css}")
    private String bucketNameCss;
 	
    @Value("${aws.bucket-name}")
    private String bucketName;

    @Value("${aws.public-url}")
    private String endpoint;
    
    @Value("${aws.public-url-css}")
    private String endpointcss;
	    
    public String uploadFile(MultipartFile file, String entityFile) {
        try {
            // Criar um nome de arquivo único com extensão
            String fileName = UUID.randomUUID() + getFileExtension(file.getOriginalFilename());

            // Criar a requisição de upload
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(file.getContentType()) // Definir o tipo de conteúdo correto
                .build();

            s3Client.putObject(objectRequest, RequestBody.fromBytes(file.getBytes()));

            return endpoint + "/" + fileName;
        } catch (Exception e) {
        	return "Erro ao salvar imagem";
        }
    }
    public String uploadCss(String conteudoCss, String nomeArquivo) {
        try {
            // Se o nome do arquivo não terminar com ".css", adicionamos a extensão
            String fileName = nomeArquivo.endsWith(".css") ? nomeArquivo : nomeArquivo + ".css";

            // Cria a requisição para o upload
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketNameCss)  // Nome do bucket no R2 ou S3
                .key("temas/" + fileName)  // Caminho e nome do arquivo no bucket (ex: temas/cliente123.css)
                .contentType("text/css")  // Tipo de conteúdo (MIME Type para CSS)
                .build();

            // Faz o upload da String de CSS para o R2/S3
            s3Client.putObject(objectRequest, RequestBody.fromString(conteudoCss));

            // Retorna a URL pública do arquivo (pode ser usado para acessar o CSS depois)
            return endpoint + "/temas/" + fileName;  // Exemplo de retorno: https://r2.example.com/temas/cliente123.css
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer upload do CSS", e);  // Trata erros de upload
        }
    }



	 private File convertMultiPartFileToFile(MultipartFile file) {
	        File convertedFile = new File(file.getOriginalFilename());
	        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
	            fos.write(file.getBytes());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return convertedFile;
	    }	
	 
	 private String getFileExtension(String fileName) {
	        if (fileName == null || !fileName.contains(".")) {
	            return "";
	        }
	        return fileName.substring(fileName.lastIndexOf("."));
	    }

}
