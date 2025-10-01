package com.practica.service;

import com.practica.dto.ProductoDto;
import com.practica.dto.VentasDto;
import com.practica.entity.Producto;
import com.practica.entity.Ventas;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface VentasService {
    List<VentasDto> listarVentas();
    List <VentasDto> listarNombre(String nombreProducto);
    List <VentasDto> ListarIdProducto(long idProducto);
    Optional <VentasDto> ListarIdVenta(long idVenta);
    VentasDto venderProducto(Long idProducto, int cantidad) throws Exception;
}
