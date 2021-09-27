package com.bibliotheque.batch.DTO;

import lombok.Data;

    @Data
    public class ExemplaireDTO {

        public int id;
        public boolean available;
        private EditionDTO edition;

    }


