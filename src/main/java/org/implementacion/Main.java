package org.implementacion;

import org.implementacion.models.Categoria;
import org.implementacion.models.Producto;
import org.implementacion.repositorio.ProductoRepositorio;
import org.implementacion.repositorio.Repositorio;

import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {
        Repositorio<Producto> repositorio = new ProductoRepositorio();
        System.out.println("========== listar ==========");
        repositorio.getAll().forEach(System.out::println);

        System.out.println("========== obtener id ==========");
        System.out.println(repositorio.porId(1L));

        System.out.println("========== insertar nuevo producto ==========");
        Producto producto = new Producto();
        producto.setNombre("Notebook Omen HP");
        producto.setPrecio(2000);
        producto.setFechaRegistro((java.sql.Date) new Date());
        Categoria categoria = new Categoria();
        categoria.setId(3L);
        producto.setCategoria(categoria);
        repositorio.guardar(producto);
        System.out.println("Producto guardado con exito");
        repositorio.getAll().forEach(System.out::println);
    }
}
