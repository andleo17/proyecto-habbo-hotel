package capaNegocio;

import capaDatos.clsJDBCConexion;
import java.sql.*;
import javax.swing.JPanel;

/**
 * @author Consuelo Fecha: 14 de Setiembre
 */
public class clsUsuario {
    clsJDBCConexion objConectar = new clsJDBCConexion();
    String strSQL;
    ResultSet rs=null;
    Connection con ; 
    
    //Método de inicio de sesión
    public String inicioSesion(String usu, String con) throws Exception{
        strSQL="select nombreCompleto from usuario where nombreusu='" + usu + "' and contrasena='" + con + "' and estado=true";
        try {
            rs=objConectar.consultarBD(strSQL);
            while(rs.next()){
                return rs.getString("nombreCompleto");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return "";
    }
   
    
    public String obtenerPreguntaSecreta(String usu) throws Exception{
        //strSQL="select pregunta from usuario where nombreusu='" + usu + "' and estado=true";
        strSQL="select pregunta from usuario where nombreusu='" + usu + "'";
        try {
            rs=objConectar.consultarBD(strSQL);
            while(rs.next()){
                return rs.getString("pregunta");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return "";
    }
    
    
    
    public String inicioSesionPregSecreta(String usu, String res) throws Exception{
        strSQL="select nombreCompleto from usuario where nombreusu='" + usu + "' and upper(respuesta)=upper('" + res + "') and estado=true";
        try {
            rs=objConectar.consultarBD(strSQL);
            while(rs.next()){
                return rs.getString("nombreCompleto");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return "";
    }
    
    public Boolean validarVigencia(String usu) throws Exception{
        strSQL="select estado from usuario where nombreusu='" + usu + "'";
        try {
            rs=objConectar.consultarBD(strSQL);
            while(rs.next()){
                return rs.getBoolean("estado");
            }
        } catch (Exception e) {
            throw new Exception("Error al validar usuario...");
        }
        return false;    
    }
    
    public void modificarContraseña(String usu, String nuevaCon) throws Exception{
        /*
        create or replace function ModificarContraseña(usu varchar(30), con varchar(30)) returns void as
        $$
        declare
        begin
            update usuario set contrasena=usu where nombreusu=usu;
        end;
        $$language'plpgsql'
        */
        //update usuario set contrasena='" + nuevaCon + "' where nombreusu='" + usu + "'
        strSQL = "select ModificarCotraseña('"+usu+"','"+nuevaCon+"')";
        objConectar.ejecutarBD(strSQL);
    }

}
