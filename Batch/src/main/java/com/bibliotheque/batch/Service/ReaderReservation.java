package com.bibliotheque.batch.Service;

import com.bibliotheque.batch.DTO.ReservationDTO;
import org.springframework.batch.item.ItemReader;

import java.net.http.HttpResponse;
import java.util.List;

public class ReaderReservation implements ItemReader<ReservationDTO> {
    public boolean batch = false;
    private int count = 0;

    public ReaderReservation() {

        batch = false;
    }


    @Override
    public ReservationDTO read() throws Exception {
        ReaderAPI readerAPI = new ReaderAPI();
        HttpResponse response = readerAPI.batchRetard();
        if (response.statusCode() != 204) {
            List<ReservationDTO> reservationDTOS = readerAPI.reservationDTOS(response);
            if (count < reservationDTOS.size()) {
                return reservationDTOS.get(count++);
            }
            count = 0;
            return null;

        }
        return null;
    }
}
