package com.challenge.hotel.model;

public class Reserva {
    private Integer id;
    private String fechaEntrada;
    private String fechaSalida;
    private Double valor;
    private String FORMAPAGO;


    public Reserva(String fEntrada,String fSalida,Double valor,String formaDePago){
        this.fechaEntrada=fEntrada;
        this.fechaSalida=fSalida;
        this.valor=valor;
        this.FORMAPAGO=formaDePago;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getFORMAPAGO() {
        return FORMAPAGO;
    }
    public String getFechaEntrada() {
        return fechaEntrada;
    }
    public String getFechaSalida() {
        return fechaSalida;
    }
    public Integer getId() {
        return id;
    }
    public Double getValor() {
        return valor;
    }
}
