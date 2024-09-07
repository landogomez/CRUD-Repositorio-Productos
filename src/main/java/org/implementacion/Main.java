package org.implementacion;

import org.implementacion.models.Categoria;
import org.implementacion.models.Producto;
import org.implementacion.rep.CategoriaRepositorio;
import org.implementacion.rep.ProductoRepositorio;
import org.implementacion.rep.Repositorio;
import org.implementacion.service.Catalogo;
import org.implementacion.service.Service;
import org.implementacion.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {

        Service service = new Catalogo();
        System.out.println("========== listar ==========");
        service.getAll().forEach(System.out::println);
        Categoria categoria = new Categoria();
        categoria.setNombre("Iluminacion");

        Producto producto = new Producto();
        producto.setNombre("Lampara led escritorio");
        producto.setPrecio(999);
        producto.setFechaRegistro(new Date());
        producto.setSku("446a5620");
        service.guardarPCC(producto,categoria);
        System.out.println("Producto guardado con exito" + producto.getId());
        service.getAll().forEach(System.out::println);

    }
}
