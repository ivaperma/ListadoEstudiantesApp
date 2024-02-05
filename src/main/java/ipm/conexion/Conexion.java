package ipm.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection getConexion() {
        Connection conexion = null;
        var baseDatos = "estudiantes_db";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario = "root";
        var password = "";
        // Cargamos la clase del driver de MySql en memoria
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return conexion;
    }

    public static void main(String[] args) {
        Conexion Conexion = null;
        var conexion = Conexion.getConexion();
        if (conexion != null)
            System.out.println("Conexion exitosa: " + conexion);
        else
            System.out.println("Error al conectarse");
    }
}
