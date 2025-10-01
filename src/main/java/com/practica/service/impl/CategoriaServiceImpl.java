package com.practica.service.impl;

import com.practica.dto.CategoriaDto;
import com.practica.entity.Categoria;
import com.practica.exceptions.ResourceNoFoundExceptions;
import com.practica.mapper.CategoriaMapper;
import com.practica.repository.CategoriaRepository;
import com.practica.service.CategoriaService;
import lombok.SneakyThrows;
import org.apache.catalina.mapper.Mapper;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CategoriaMapper categoriaMapper;

    @Override
    public CategoriaDto crearCategoria(CategoriaDto categoriaDto) throws BadRequestException {
        if(categoriaRepository.existsByNombreCategoria(categoriaDto.getNombreCategoria())) {
            throw new BadRequestException("El nombre ya existe");
        }
        Categoria categoria = categoriaMapper.toEntity(categoriaDto);

        Categoria nuevaCategoria = categoriaRepository.save(categoria);

        return categoriaMapper.toDTO(nuevaCategoria);
    }

    @Override
    public List<CategoriaDto> listarCategorias() {

        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoriaDto> ObtenerCategoriaId(Long idCategoria) {
        Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);
        return categoria.map(categoriaMapper ::toDTO);
    }

    @Override

    public CategoriaDto actualizarCategoria(Long idCategoria, CategoriaDto categoriaDto) {
        // Buscar si existe la categoría en la BD
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(idCategoria);

        if (optionalCategoria.isPresent()) {
            Categoria categoriaExistente = optionalCategoria.get();

            // Actualizar solo los campos necesarios
            categoriaExistente.setNombreCategoria(categoriaDto.getNombreCategoria());

            // Guardar cambios
            Categoria categoriaGuardada = categoriaRepository.save(categoriaExistente);

            // Convertir a DTO
            return categoriaMapper.toDTO(categoriaGuardada);
        } else {
            return null; // El controlador devuelve 404
        }
    }

    @Override
    public void eliminarCategoria(Long idCategoria) throws Exception {
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(idCategoria);
        if(!categoriaExistente.isPresent()){
            throw new ResourceNoFoundExceptions("Categoria no encontrada");
        }
        categoriaRepository.deleteById(idCategoria);
    }
}
