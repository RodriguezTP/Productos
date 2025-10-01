package com.practica.mapper;

import com.practica.dto.CategoriaDto;
import com.practica.dto.ProductoDto;
import com.practica.entity.Categoria;
import com.practica.entity.Producto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
    @Autowired
    private ModelMapper modelMapper;

    public CategoriaDto toDTO(Categoria categoria){
        return modelMapper.map(categoria,CategoriaDto.class);
    }
    public Categoria toEntity(CategoriaDto categoriaDto){
        return  modelMapper.map(categoriaDto,Categoria.class);
    }
}
