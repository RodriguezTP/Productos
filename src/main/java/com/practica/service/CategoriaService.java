package com.practica.service;

import com.practica.dto.CategoriaDto;
import com.practica.entity.Categoria;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.ListableBeanFactory;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    CategoriaDto crearCategoria(CategoriaDto categoria) throws BadRequestException;
    List<CategoriaDto> listarCategorias();
    Optional<CategoriaDto> ObtenerCategoriaId(Long idCategoria);
    CategoriaDto actualizarCategoria(Long idCategoria, CategoriaDto categoria) throws Exception;
    void eliminarCategoria(Long idCategoria) throws Exception;
}
