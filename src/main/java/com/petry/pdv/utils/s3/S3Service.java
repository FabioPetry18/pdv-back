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

    @Value("${aws.bucket-name}")
    private String bucketName;

    @Value("${aws.endpoint}")
    private String endpoint;
	    
    public String uploadFile(MultipartFile file, String entityFile) {
        try {
            // Criar um nome de arquivo único com extensão
            String fileName = entityFile + "-" + UUID.randomUUID() + getFileExtension(file.getOriginalFilename());

            // Criar a requisição de upload
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(file.getContentType()) // Definir o tipo de conteúdo correto
                .build();

            s3Client.putObject(objectRequest, RequestBody.fromBytes(file.getBytes()));

            return endpoint + "/" + bucketName + "/" + fileName;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer upload do arquivo", e);
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
