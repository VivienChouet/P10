package com.bibliotheque.batch.Service;

import com.bibliotheque.batch.DTO.AttenteDTO;
import org.springframework.batch.item.ItemWriter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class WriterAttente implements  ItemWriter<AttenteDTO>{

    public WriterAttente(){

    }

    HttpClient httpClient = HttpClient.newBuilder().build();

    @Override
    public void write(List<? extends AttenteDTO> attenteDTOS) throws Exception {
        System.out.println("size writer : " + attenteDTOS.size());

        HttpRequest requestPost = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/batch/attente/"+attenteDTOS.get(0).id)).setHeader("Content-Type" , "application/json").POST(HttpRequest.BodyPublishers.ofString("json")).build();
        HttpResponse response = null;

        try {
            response = httpClient.send(requestPost, HttpResponse.BodyHandlers.ofString());
            System.out.println("Body    : " + attenteDTOS );
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
