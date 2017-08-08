package com.rma.ipanduric.weddie;

/**
 * Created by ipand on 8.8.2017..
 */

public class PotrebeItem {

        private String pnaziv;
        private float prezultat;
        private String pjedinica;

        public PotrebeItem(String naziv, float rezultat, String jedinica ) {
            this.pnaziv = naziv;
            this.prezultat = rezultat;
            this.pjedinica = jedinica;

        }

        public String getpNaziv() {
            return this.pnaziv;
        }

        public float getpRezultat() {
            return this.prezultat;
        }

        public String getpJedinica() { return this.pjedinica; }
    }


