package com.practica.entity;

import com.practica.entity.EstadoProducto;
import jakarta.persistence.*;

import lombok.Data;


@Entity
@Data

public class Producto{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_producto")
        private long idProducto;
        @Column(name = "nombre_producto", nullable = false, length = 100)
        private String nombreProducto;
        @Column(name = "description")
        private String Descripcion;
        @Column(name = "precio", nullable = false)
        private double precio;
        @Column(name = "cantidad", nullable = false)
        private int cantidad;
        @Enumerated(EnumType.STRING)
        @Column(name = "Estado")
        private EstadoProducto estadoProducto;

        @ManyToOne
        @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
        private Categoria categoria;


    public Producto() {
    }

    public Producto(int cantidad, String descripcion, EstadoProducto estadoProducto, long idProducto, String nombreProducto, double precio) {
        this.cantidad = cantidad;
        Descripcion = descripcion;
        this.estadoProducto = estadoProducto;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
    public EstadoProducto getEstadoProducto() {
        return estadoProducto;
    }
    public void setEstadoProducto(EstadoProducto estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }



}
