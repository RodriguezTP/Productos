package com.practica.mapper;

import com.practica.dto.ProductoDto;
import com.practica.entity.Producto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Producto toEntity(ProductoDto productoDto){
        return modelMapper.map(productoDto, Producto.class);

    }

    public void toEntity(ProductoDto productoDto, Producto productoExistente){
        modelMapper.map(productoDto, productoExistente);
    }

    public ProductoDto toDto(Producto producto){
        return modelMapper.map(producto,ProductoDto.class);
    }
}
