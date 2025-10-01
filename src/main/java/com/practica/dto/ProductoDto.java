package com.practica.dto;

import com.practica.entity.Categoria;
import com.practica.entity.EstadoProducto;
import com.practica.entity.Producto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.internal.util.StringHelper;

@Data


public class ProductoDto {

    private Long idProducto;
    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres")
    private String nombreProducto;
    @Size(max = 250, message = "La descripcion no debe exceder los 250 caracteres")
    private String descripcion;
    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio debe ser mayor o igual a 0")
    private double precio;
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private int cantidad;
    @NotNull(message = "El estado del producto es obligatorio")
    private EstadoProducto estadoProducto;
    private Categoria categoria;

    public ProductoDto(int cantidad, Categoria categoria, String descripcion, EstadoProducto estadoProducto, String nombreProducto, double precio) {
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.estadoProducto = estadoProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
    }

    public ProductoDto() {

    }


    public @NotNull(message = "La cantidad es obligatoria") @Min(value = 1, message = "La cantidad debe ser al menos 1") int getCantidad() {
        return cantidad;
    }

    public void setCantidad(@NotNull(message = "La cantidad es obligatoria") @Min(value = 1, message = "La cantidad debe ser al menos 1") int cantidad) {
        this.cantidad = cantidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public @Size(max = 250, message = "La descripcion no debe exceder los 250 caracteres") String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@Size(max = 250, message = "La descripcion no debe exceder los 250 caracteres") String descripcion) {
        this.descripcion = descripcion;
    }

    public @NotNull(message = "El estado del producto es obligatorio") EstadoProducto getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(@NotNull(message = "El estado del producto es obligatorio") EstadoProducto estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public @NotBlank(message = "El nombre no puede estar vacio") @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres") String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(@NotBlank(message = "El nombre no puede estar vacio") @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres") String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public @NotNull(message = "El precio es obligatorio") @Min(value = 0, message = "El precio debe ser mayor o igual a 0") double getPrecio() {
        return precio;
    }

    public void setPrecio(@NotNull(message = "El precio es obligatorio") @Min(value = 0, message = "El precio debe ser mayor o igual a 0") double precio) {
        this.precio = precio;
    }
}
