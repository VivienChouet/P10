package com.bibliotheque.batch.Service;

import com.bibliotheque.batch.DTO.AttenteDTO;
import org.springframework.batch.item.ItemReader;

import java.net.http.HttpResponse;
import java.util.List;

public class ReaderAttente implements ItemReader<AttenteDTO> {

    public boolean batch = false;
    private int count = 0;

    public ReaderAttente() {
        batch = true;
    }

    @Override
    public AttenteDTO read() throws Exception {
        ReaderAPI readerAPI = new ReaderAPI();
        HttpResponse response = readerAPI.batchAttente();
        if (response.statusCode() != 204) {
            List<AttenteDTO> attenteDTOS = readerAPI.attenteDTOS(response);
            if (count < attenteDTOS.size()) {
                return attenteDTOS.get(count++);
            }
            count = 0;
            return null;

        }

        return null;
    }

}