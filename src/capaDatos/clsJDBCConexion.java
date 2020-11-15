package capaDatos;

/**
 * @author Consuelo, Fecha: 9 Setiembre 2020
 */
import java.sql.*;

//No olvidar de importar en Libraries el JAR postgressql

public class clsJDBCConexion {
    private String driver, url, user, password;
    private Connection con;
    private Statement sent = null;

    // Constructor
    public clsJDBCConexion() {
        this.driver = "org.postgresql.Driver";
        this.url = "jdbc:postgresql://localhost:5432/BdHotel";
        this.user = "postgres";
        // Cambiar contraseña
        this.password = "123456789";
        this.con = null;
    }

    // Conectar
    public void conectar() throws Exception {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new Exception(ex);
        }
    }

    // Desconectar
    public void desconectar() throws Exception {
        try {
            con.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    // Retorna la variable de conexión
    public Connection getConnection() {
        return con;
    }

    public Connection getCon() {
        return con;
    }

    // Ejecutar una consulta SELECT
    public ResultSet consultarBD(String strSQL) throws Exception {
        ResultSet rs = null;
        try {
            conectar();
            sent = con.createStatement();
            rs = sent.executeQuery(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (con != null) {
                desconectar();
            }
        }
    }

    public void ejecutarBD(String strSQL) throws Exception {
        try {
            conectar();
            sent = con.createStatement();
            sent.executeUpdate(strSQL);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (con != null) {
                desconectar();
            }
        }
    }

}
