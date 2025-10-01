package com.practica.controller;

import com.practica.dto.CategoriaDto;
import com.practica.entity.Categoria;
import com.practica.exceptions.ResourceNoFoundExceptions;
import com.practica.service.CategoriaService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;
    @PostMapping
    public ResponseEntity<CategoriaDto> crearCategoria(@Valid  @RequestBody CategoriaDto categoria) throws BadRequestException {
        CategoriaDto nuevaCategoria = categoriaService.crearCategoria(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }
    @GetMapping("/listar")
    public ResponseEntity<List<CategoriaDto>> listarCategorias(){
        List<CategoriaDto> categorias = categoriaService.listarCategorias();
        return new ResponseEntity<>(categorias,HttpStatus.OK);

    }
    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaDto> obtenerCategoriasId(@PathVariable Long idCategoria) throws Exception {
        Optional<CategoriaDto> categoriaOptional = categoriaService.ObtenerCategoriaId(idCategoria);
        if(categoriaOptional.isPresent()){
            return new ResponseEntity<>(categoriaOptional.get(), HttpStatus.OK);
        } else {
            throw new Exception( "Categoria no encontrada");
        }

    }
    @PutMapping("/actualizar/{idCategoria}")
    public ResponseEntity<?> actualizarCategoria(
            @PathVariable Long idCategoria,
            @RequestBody CategoriaDto categoria) {
        try {
            CategoriaDto categoriaActualizada = categoriaService.actualizarCategoria(idCategoria, categoria);

            if (categoriaActualizada != null) {
                return new ResponseEntity<>(categoriaActualizada, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No existe la categoría con id = " + idCategoria, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/borrar/{idCategoria}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long idCategoria) {
        try {
            categoriaService.eliminarCategoria(idCategoria);
            return new ResponseEntity<>("Categoría eliminada correctamente", HttpStatus.NO_CONTENT);
        } catch (ResourceNoFoundExceptions e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
