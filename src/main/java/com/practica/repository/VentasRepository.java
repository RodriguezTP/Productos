package com.practica.repository;

import com.practica.entity.Producto;
import com.practica.entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VentasRepository extends JpaRepository<Ventas,Long> {
    Optional<Ventas> findByProducto_NombreProducto (String nombreProducto);
    Optional<Ventas> findByProducto_IdProducto (long idProducto);
    List<Ventas> findAllByOrderByIdVentaAsc();
}
