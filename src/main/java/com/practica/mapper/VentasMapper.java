package com.practica.mapper;

import com.practica.dto.VentasDto;
import com.practica.entity.Ventas;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VentasMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Ventas toEntity(VentasDto ventaDto){
        return modelMapper.map(ventaDto, Ventas.class);
    }

    public void toEntity(VentasDto ventaDto, Ventas ventaExistente){
        modelMapper.map(ventaDto, ventaExistente);
    }

    public VentasDto toDto(Ventas venta){
        return modelMapper.map(venta, VentasDto.class);
    }
}
