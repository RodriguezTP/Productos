package com.practica.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.practica.entity.Ventas;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class VentasDto {
    private Long idVenta;
    private Long idProducto;
    private String nombreProducto;
    private double precioUnitario;
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private int cantidadVendida;
    private double totalVenta;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaVenta;

    public VentasDto() {}

    public VentasDto(Ventas ventas) {
        this.idVenta = ventas.getIdVenta();
        this.idProducto = ventas.getProducto().getIdProducto();
        this.nombreProducto = ventas.getProducto().getNombreProducto();
        this.precioUnitario = ventas.getPrecioUnitario();
        this.cantidadVendida = ventas.getCantidadVendida();
        this.totalVenta = ventas.getTotalVenta();
        this.fechaVenta = ventas.getFechaVenta();

    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }


    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }


    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
