package com.Prevozi.Taxi;

import net.bytebuddy.build.ToStringPlugin;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name="prevozi")
public class Prevozi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 45)
    private String ime;
    @Column(nullable = false, length = 45)
    private String priimek;
    @Column(nullable = false, length = 45)
    private String email;
    @Column(nullable = false, length = 45)
    private String telefon;
    @Column(nullable = false, length = 255)
    private String z_destinacija;
    @Column(nullable = false, length = 255)
    private String k_destinacija;
    @Column(nullable = true, length = 45)
    private LocalDate datum;
    @Column(nullable = true, length = 45)
    private LocalTime cas;
    @Column(nullable = true, length = 45)
    private int voznik;






    @Column
    private String casString;
    @Column
    private String datumString;

    public Prevozi() {
    }

    public String getCasString() {
        return casString;
    }

    public void setCasString(String casString) {
        this.casString = casString;
    }

    public void setDatumString(String datumString) {
        this.datumString = datumString;
    }

    public String getDatumString() {
        return datumString;
    }

    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getZ_destinacija() {
        return z_destinacija;
    }

    public String getK_destinacija() {
        return k_destinacija;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public LocalTime getCas() {
        return cas;
    }

    public int getVoznik() {
        return voznik;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setZ_destinacija(String z_destinacija) {
        this.z_destinacija = z_destinacija;
    }

    public void setK_destinacija(String k_destinacija) {
        this.k_destinacija = k_destinacija;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public void setCas(LocalTime cas) {
        this.cas = cas;
    }

    public void setVoznik(int voznik) {
        this.voznik = voznik;
    }




    @Override
    public String toString() {
        return "prevozi{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", priimek='" + priimek + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", z_destinacija='" + z_destinacija + '\'' +
                ", k_destinacija='" + k_destinacija + '\'' +
                ", datum=" + datum +
                ", cas=" + cas +
                ", voznik=" + voznik +
                '}';
    }
}
