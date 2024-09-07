package org.implementacion.repositorio;

import org.implementacion.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositorio implements Repositorio<Categoria> {
    private Connection conn;

    public CategoriaRepositorio(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> getAll() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categoria")) {
            while (rs.next()) {
                categorias.add(createCategoria(rs));
            }
        }
        return categorias;
    }

    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = null;
        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categoria WHERE id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    categoria = createCategoria(rs);
            }
        }
        return categoria;
    }

    @Override
    public Categoria guardar(Categoria categoria) throws SQLException {
        return null;
    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    private static Categoria createCategoria(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();
        c.setId(rs.getLong("id"));
        c.setNombre(rs.getString("nombre"));
        return c;
    }
}
