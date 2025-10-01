package com.practica.controller;
import com.practica.dto.ProductoDto;
import com.practica.entity.EstadoProducto;
import com.practica.entity.Producto;
import com.practica.service.ProductoService;
import com.practica.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @PostMapping("/registrar/{categoriaId}")
    public ResponseEntity<?> registrarProducto(
            @PathVariable Long categoriaId,
            @RequestParam("nombreProducto") String nombreProducto,
            @RequestParam("descripcion") String Descripcion,
            @RequestParam("precio") double precio,
            @RequestParam("cantidad") int cantidad,
            @RequestParam("estado") EstadoProducto estado)
    {
        ProductoDto producto = new ProductoDto();
        producto.setNombreProducto(nombreProducto);
        producto.setDescripcion(Descripcion);
        producto.setPrecio(precio);
        producto.setCantidad (cantidad);
        producto.setEstadoProducto(estado);

        ProductoDto nuevoProducto = productoService.registrarProducto(producto,categoriaId);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProductoDto>> listarProductos(){
        List<ProductoDto> productosDto = productoService.listarProductos();
        return ResponseEntity.ok(productosDto);
    }
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<?> buscarNombre(@PathVariable String nombre){
        Optional<ProductoDto> producto = productoService.buscarNombre(nombre);
        return producto.isPresent()? ResponseEntity.ok(producto.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");

    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarNombre(@PathVariable long id){
        Optional<ProductoDto> producto = productoService.buscarPorId(id);
        return producto.isPresent()? ResponseEntity.ok(producto.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");

    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarProducto(
            @PathVariable Long id,
            @RequestParam("nombreProducto") String nombreProducto,
            @RequestParam("descripcion") String Descripcion,
            @RequestParam("precio") double precio,
            @RequestParam("cantidad") int cantidad,
            @RequestParam("estado") EstadoProducto estado
    ){
        try{
            ProductoDto productoActualizado = new ProductoDto();
            productoActualizado.setNombreProducto(nombreProducto);
            productoActualizado.setDescripcion(Descripcion);
            productoActualizado.setPrecio(precio);
            productoActualizado.setCantidad(cantidad);
            productoActualizado.setEstadoProducto(estado);

            ProductoDto productoBBDD = productoService.actualizarProducto(id, productoActualizado);
            return ResponseEntity.ok(productoBBDD);

        } catch (Exception exception){
            System.out.println(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        try{
            productoService.eliminarProducto(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ProductoDto>> listarProductosByEstado(@PathVariable EstadoProducto estado){
        List<ProductoDto> productos = productoService.obtenerPorEstado(estado);
        return ResponseEntity.ok(productos);

    }

}
