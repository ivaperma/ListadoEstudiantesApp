package ipm.dao;

import ipm.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ipm.conexion.Conexion.getConexion;

public class EstudianteDao {
    public List<Estudiante> listar() {
        List<Estudiante> estudiantes = new ArrayList<>();
        // Prepara la sentencia de SQL que ejecutamos a la base de datos
        PreparedStatement ps;
        // Nos permite almacenar el resultado de la consulta
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                var estudiante = new Estudiante();
                // Recuperamos el registro de la base de datos que estamos recorriendo.
                // Le pedimos el valos de la columna id_estudiante y lo asignamos a estudiante.
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return estudiantes;
    }

    public static void main(String[] args) {
        var estudianteDao = new EstudianteDao();
        System.out.println("Listado de estudiantes: ");
        List<Estudiante> estudiantes = estudianteDao.listar();
        estudiantes.forEach(System.out::println);
    }
}
