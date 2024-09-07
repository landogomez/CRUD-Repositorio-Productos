package org.implementacion;

import org.implementacion.models.Categoria;
import org.implementacion.models.Producto;
import org.implementacion.repositorio.CategoriaRepositorio;
import org.implementacion.repositorio.ProductoRepositorio;
import org.implementacion.repositorio.Repositorio;
import org.implementacion.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getConnection()) {

            if (conn.getAutoCommit())
                conn.setAutoCommit(false);
            try {
                Repositorio<Categoria> repositorioCategoria = new CategoriaRepositorio(conn);
                System.out.println("========== insertar categoria ==========");
                Categoria categoria = new Categoria();
                categoria.setNombre("Electrohogar");
                Categoria nuevacategoria = repositorioCategoria.guardar(categoria);
                System.out.println("Categoria guardada con exito " + nuevacategoria.getId());

                Repositorio<Producto> repositorio = new ProductoRepositorio(conn);
                System.out.println("========== listar ==========");
                repositorio.getAll().forEach(System.out::println);

                System.out.println("========== obtener id ==========");
                System.out.println(repositorio.porId(1L));

                System.out.println("========== insertar nuevo producto ==========");
                Producto producto = new Producto();
                producto.setNombre("Refrigerador LG");
                producto.setPrecio(14000);
                producto.setFechaRegistro(new Date());
                producto.setSku("456a5620");


                producto.setCategoria(nuevacategoria);
                repositorio.guardar(producto);
                System.out.println("Producto guardado con exito" + producto.getId());
                repositorio.getAll().forEach(System.out::println);

                conn.commit();
            }catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        }
    }
}
