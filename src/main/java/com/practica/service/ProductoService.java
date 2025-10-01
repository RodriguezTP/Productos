package com.practica.service;

import com.practica.dto.ProductoDto;
import com.practica.entity.EstadoProducto;
import com.practica.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    ProductoDto registrarProducto(ProductoDto productodto, Long categoriaId);
    List<ProductoDto> listarProductos();
    Optional <ProductoDto> buscarNombre(String nombre);
    Optional<ProductoDto> buscarPorId (long idProducto);
    ProductoDto actualizarProducto (Long idProducto, ProductoDto productoDto) throws Exception;
    void eliminarProducto(long idProducto)throws Exception;
    List<ProductoDto> obtenerPorEstado(EstadoProducto estadoProducto);
}