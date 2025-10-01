package com.practica.service.impl;

import com.practica.dto.ProductoDto;
import com.practica.entity.Categoria;
import com.practica.entity.EstadoProducto;
import com.practica.entity.Producto;
import com.practica.exceptions.ResourceNoFoundExceptions;
import com.practica.mapper.ProductoMapper;
import com.practica.repository.CategoriaRepository;
import com.practica.repository.ProductoRepository;
import com.practica.service.ProductoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoMapper productoMapper;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public ProductoDto registrarProducto(ProductoDto productoDto, Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Producto producto = productoMapper.toEntity(productoDto);

        producto.setCategoria(categoria);

        Producto nuevo = productoRepository.save(producto);
        return productoMapper.toDto(nuevo);
    }

    @Override
    public List<ProductoDto> listarProductos() {
        List<Producto> productos = productoRepository.findAllByOrderByIdProductoAsc();
        return productos.stream().map(productoMapper::toDto).toList();
    }

    @Override
    public Optional<ProductoDto> buscarNombre(String nombre) {
        Optional<Producto> producto = productoRepository.findByNombreProducto(nombre);
        return producto.map(productoMapper::toDto);
    }

    @Override
    public Optional<ProductoDto> buscarPorId(long idProducto) {
        Optional<Producto> producto = productoRepository.findById(idProducto);
        return producto.map(productoMapper::toDto);
    }

    @Override


    public ProductoDto actualizarProducto(Long idProducto, ProductoDto productoDto) {
        Producto productoExistente = productoRepository.findById(idProducto)
                .orElseThrow(() -> new EntityNotFoundException("Producto con id " + idProducto + " no encontrado"));

        // Actualizar campos básicos
        productoExistente.setNombreProducto(productoDto.getNombreProducto());
        productoExistente.setDescripcion(productoDto.getDescripcion());
        productoExistente.setPrecio(productoDto.getPrecio());
        productoExistente.setCantidad(productoDto.getCantidad());
        productoExistente.setEstadoProducto(productoDto.getEstadoProducto());

        if (productoDto.getCategoria() != null && productoDto.getCategoria().getIdCategoria() != null) {
            Categoria categoria = categoriaRepository.findById(productoDto.getCategoria().getIdCategoria())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Categoría con id " + productoDto.getCategoria().getIdCategoria() + " no encontrada"));
            productoExistente.setCategoria(categoria);
        }

        Producto productoGuardado = productoRepository.save(productoExistente);
        return productoMapper.toDto(productoGuardado);
    }


    @Override
    public void eliminarProducto(long idProducto) throws Exception{
        productoRepository.findById(idProducto)
                .orElseThrow(() -> new ResourceNoFoundExceptions("Producto con Id: "+ idProducto + " no encontrado"));
        productoRepository.deleteById(idProducto);

    }


    @Override
    public List<ProductoDto> obtenerPorEstado(EstadoProducto estadoProducto) {
        List<Producto> productos = productoRepository.findByEstadoProducto(estadoProducto);
        return productos.stream()
                .map(productoMapper::toDto)
                .toList();
    }
}
