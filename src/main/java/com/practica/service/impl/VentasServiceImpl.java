package com.practica.service.impl;

import com.practica.dto.ProductoDto;
import com.practica.dto.VentasDto;
import com.practica.entity.EstadoProducto;
import com.practica.entity.Producto;
import com.practica.entity.Ventas;
import com.practica.mapper.VentasMapper;
import com.practica.repository.ProductoRepository;
import com.practica.repository.VentasRepository;
import com.practica.service.VentasService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VentasServiceImpl implements VentasService {
    @Autowired
    private VentasRepository ventasRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private VentasMapper ventasMapper;
    @Override
    public List<VentasDto> listarVentas(){
        List<Ventas> ventas = ventasRepository.findAllByOrderByIdVentaAsc();
        return ventas.stream().map(ventasMapper::toDto).toList();
    }
    @Override
    public List<VentasDto> ListarIdProducto(long idProducto) {
        Optional<Ventas> venta = ventasRepository.findByProducto_IdProducto(idProducto);
        return venta.stream().map(ventasMapper::toDto).toList();
    }
    @Override
    public List<VentasDto> listarNombre(String nombreProducto) {
        Optional<Ventas> venta = ventasRepository.findByProducto_NombreProducto(nombreProducto);
        return venta.stream().map(ventasMapper::toDto).toList();
    }

    @Override
    public VentasDto venderProducto(Long idProducto, int cantidad) throws Exception {
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new Exception("Producto no encontrado con id: " + idProducto));

        if (producto.getCantidad() < cantidad) {
            throw new Exception("Stock insuficiente para el producto: " + producto.getNombreProducto());
        }
        producto.setCantidad(producto.getCantidad() - cantidad);
        if (producto.getCantidad() == 0) {
            producto.setEstadoProducto(EstadoProducto.NO_DISPONIBLE);
        }
        productoRepository.save(producto);

        Ventas venta = new Ventas();
        venta.setProducto(producto);
        venta.setCantidadVendida(cantidad);
        venta.setPrecioUnitario(producto.getPrecio());
        venta.setTotalVenta(producto.getPrecio() * cantidad);

        Ventas ventanueva= ventasRepository.save(venta);

        return ventasMapper.toDto(ventanueva);
    }
    @Override
    public Optional <VentasDto> ListarIdVenta(long idVenta){
        Optional <Ventas> venta = ventasRepository.findById(idVenta);
        return venta.map(ventasMapper::toDto);
    }

}
