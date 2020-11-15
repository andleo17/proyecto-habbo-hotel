
package capaNegocio;

import capaDatos.*;
import java.sql.*;
/**
/**
 *
 * @author ROJASB
 */
public class clsTipoServi {
    clsJDBCConexion objConectar = new clsJDBCConexion();
    //String strSQL;
    ResultSet rs = null;
    Connection con;
    Statement sent;
   
    public Integer generarCodigoTipoSer() throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select COALESCE(max (codigots),0)+1 as codigo from tipo_servicio");
            rs = sentencia.executeQuery();
            while(rs.next()){
                return rs.getInt("codigo"); //el codigo es del alias del CODIGO FROM
            }
             return 0 ; 
        } catch (Exception e) {
            throw  new Exception("Error al generar en nuevo codigo: "+e.getMessage());
        }
    }
    
    public Boolean verificacodSer( String cod) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select * from tipo_servicio where codigots=?");
            sentencia.setInt(1, Integer.parseInt(cod));
            rs = sentencia.executeQuery();
            if( rs.next()){ 
                return false ; // quiere decir que al encontrar el nro de hab retornaria false
            } else {
                return true; // quiere decir que no encontro por lo que si puede registrar
            }
        } catch (Exception e) {
            throw new Exception ("error al verificar servicio" +e.getMessage());
        }
        
    }
    
    public void registarTiposer(String cod , String nom, String descr,Double pre,  Boolean vig ) throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia1 = con.prepareCall("selec * from tipo_servicio where nombre=?");
            sentencia1.setString(1, nom);     
            rs = sentencia1.executeQuery();
            if(!rs.next()){
               CallableStatement sentencia = con.prepareCall("insert into tipo_servicio values(?,?,?,?,?)");
               sentencia.setInt(1, Integer.parseInt(cod));
               sentencia.setString(2, nom);
               sentencia.setString(3, descr);
               sentencia.setDouble(4, pre);
               sentencia.setBoolean(5, vig);
               sentencia.executeUpdate(); 
            }
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al registrar" +e.getMessage())  ;    
        }
    }
    

    public void modificartiposer(String cod, String nom ,String des , double prec, Boolean vig ) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("update tipo_servicio set nombre=?, descripcion=?,precio=?, vigencia=? where codigots=?");
            sentencia.setString(1, nom);
            sentencia.setString(2, des);
            sentencia.setDouble(3, prec);
            sentencia.setBoolean(4, vig);
            sentencia.setInt(5, Integer.parseInt(cod));
            sentencia.executeUpdate(); 
        } catch (Exception e) {
            throw  new Exception("Error al modificar tipo de habitación: "+e.getMessage()) ;
        }
    }
    
    public ResultSet buscarServi(String  num ) throws Exception { 
         
         try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select * from tipo_servicio where codigots=?");
            sentencia.setInt(1, Integer.parseInt(num));
            rs = sentencia.executeQuery();
            return rs;
        } catch (Exception e) {
            throw new  Exception ("Error al buscar " +e.getMessage());
        }
    }
    
    public ResultSet consultarServi(Integer numH) throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select h.descripcion,h.estado,h.vigencia,th.nombre from habitacion as h inner join tipo_habitacion as th on h.codigoth=th.codigoth where h.numerohab=?");
            sentencia.setInt(1, numH);
            rs = sentencia.executeQuery();
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al consultar Servi" +e.getMessage());
        }
    }
    
    public void eliminarSer(String num) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            con.setAutoCommit(false);
            CallableStatement sentencia1 = con.prepareCall("delete from tipo_servicio where codigots=?");
            sentencia1.setInt(1, Integer.parseInt(num));
            sentencia1.executeUpdate();
            CallableStatement sentencia2 = con.prepareCall("delete from servicio where codigots=?");
            sentencia2.setInt(1, Integer.parseInt(num));
            sentencia2.executeUpdate();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error cuando eliminas el servicio " +e.getMessage());
        }
    }
    
    public void dardebajaServi(String num) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            con.setAutoCommit(false);
            CallableStatement sentencia1 = con.prepareCall("update tipo_servicio set vigencia=false where codigots=?");
            sentencia1.setInt(1, Integer.parseInt(num));
            sentencia1.executeUpdate();
            CallableStatement sentencia2 = con.prepareCall("update servicio set estado=false where codigots=?");
            sentencia2.setInt(1, Integer.parseInt(num));
            sentencia2.executeUpdate();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error cuando das de baja el servicio " +e.getMessage());
        }
    }
     
     public ResultSet listarServ() throws Exception { 
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select * from tipo_servicio");
            rs = sentencia.executeQuery();
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar: "+e.getMessage());
        }
        
    }
}
