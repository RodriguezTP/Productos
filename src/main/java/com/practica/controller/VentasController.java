package com.practica.controller;

import com.practica.dto.ProductoDto;
import com.practica.dto.VentasDto;
import com.practica.repository.VentasRepository;
import com.practica.service.VentasService;
import com.practica.service.impl.VentasServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/venta")
public class VentasController {
    @Autowired
    private VentasService ventasService;
    @PostMapping("/{idProducto}")
    public ResponseEntity<?> venderProducto(@PathVariable Long idProducto, @RequestParam int cantidad){
        try{
            VentasDto venta = ventasService.venderProducto(idProducto,cantidad);
            return ResponseEntity.ok(venta);

        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }

    }
    @GetMapping("/listar")
    public ResponseEntity<?> listarVentas(){
        List<VentasDto> venta = ventasService.listarVentas();
        return ResponseEntity.ok(venta);

    }
    @GetMapping("/listar/idproducto/{idProducto}")
    public ResponseEntity<?> ListarIdProducto(@PathVariable long idProducto){
        List <VentasDto> venta = ventasService.ListarIdProducto(idProducto);
        return ResponseEntity.ok(venta);
    }
    @GetMapping("/listar/{nombreProducto}")
    public ResponseEntity<?> listarNombreProducto(@PathVariable String nombreProducto){
        List <VentasDto> venta = ventasService.listarNombre(nombreProducto);
        return ResponseEntity.ok(venta);
    }
    @GetMapping("/listar/idventa/{idVenta}")
    public ResponseEntity<?> ListarIdVenta(@PathVariable long idVenta){
        Optional <VentasDto> venta = ventasService.ListarIdVenta(idVenta);
        return ResponseEntity.ok(venta);


    }






}
