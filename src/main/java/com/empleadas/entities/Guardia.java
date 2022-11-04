package com.empleadas.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Guardia {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    @ManyToOne
    @JoinColumn(name = "empleada_nombre")
    private Empleada empleada;
    private Date ingreso;
    private Date salida;
    private Boolean activa;

    public Guardia() {
        setIngreso(new Date());
        setActiva(true);
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public Date getIngreso() {
        return ingreso;
    }

    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }

    public Date getSalida() {
        return salida;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Empleada getEmpleada() {
        return empleada;
    }

    public void setEmpleada(Empleada empleada) {
        this.empleada = empleada;
    }
}
