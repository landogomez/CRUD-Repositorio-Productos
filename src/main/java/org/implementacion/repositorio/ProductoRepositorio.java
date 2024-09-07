package org.implementacion.repositorio;

import org.implementacion.models.Categoria;
import org.implementacion.models.Producto;
import org.implementacion.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorio implements Repositorio<Producto> {
    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getConnection();
    }
    @Override
    public List<Producto> getAll() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select p.*, c.nombre as categoria from productos as p " +
                     "inner join categorias as c on (p.id_categoria = c.id)")){
            while (rs.next()) {
                Producto p = crearProducto(rs);
                productos.add(p);
            }
        }
        return productos;
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement("select p.*, c.nombre as categoria from productos as p "
                     +"inner join categorias as c on (p.id_categoria = c.id) where p.id = ?")) {
            stmt.setLong(1,id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    producto = crearProducto(rs);
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql;
        if (producto.getId() != null && producto.getId() > 0)
            sql = "UPDATE productos SET nombre = ?, precio=?, id_categoria=?, sku=? where id=?";
        else
            sql = "INSERT INTO productos(nombre, precio, id_categoria, sku, fecha_registro) values(?,?,?,?,?)";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)){

            stmt.setString(1, producto.getNombre());
            stmt.setLong(2, producto.getPrecio());
            stmt.setLong(3, producto.getCategoria().getId());
            stmt.setString(4, producto.getSku());

            if (producto.getId() != null && producto.getId() > 0)
                stmt.setLong(5, producto.getId());
            else
                stmt.setDate(5, new Date(producto.getFechaRegistro().getTime()));

            stmt.executeUpdate();

        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement("delete from productos where id=?")){
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }

    }

    private static Producto crearProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setFechaRegistro(rs.getDate("fecha_registro"));
        p.setSku(rs.getString("sku"));
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("id_categoria"));
        categoria.setNombre(rs.getString("categoria"));
        p.setCategoria(categoria);
        return p;
    }

}
