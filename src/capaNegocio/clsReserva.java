/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaNegocio;

import capaDatos.clsJDBCConexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author ROJASB
 */
public class clsReserva {
    clsJDBCConexion objConectar = new clsJDBCConexion();
    String strSQL;
    ResultSet rs = null;
    Connection con;
    Statement sent;
    
    public int codigoReserva() throws Exception{
          try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select COALESCE(max (numerores),0)+1 as codigo from reserva");
            rs=sentencia.executeQuery();
            if(rs.next()){
                return rs.getInt("codigo");
            }else{
                return 0;
            }
        } catch (Exception e) {
            throw new Exception("Error al generar el código" +e.getMessage())  ; 
        }    
    }
    
    public void registarReserva(int cod,Date ini,Date fin,boolean estado, boolean confirmacion, String referencia, int hab, String dnihue, String dniemp) throws Exception{
        try {
            boolean valor = false;
            boolean registrar = false;
            objConectar.conectar();
            con = objConectar.getConnection();
            con.setAutoCommit(false);
            CallableStatement sentencia1 = con.prepareCall("select vigencia from habitacion where numerohab=? and estado='D'");
            sentencia1.setInt(1, hab);
            rs = sentencia1.executeQuery();
            while (rs.next()){
                valor = rs.getBoolean("vigencia");
            }
            if(valor){
             strSQL = " insert into reserva values ("+cod+",'"+ new java.sql.Date(ini.getTime())+"','"+new java.sql.Date(fin.getTime())+"',"+estado+","+confirmacion+", '"+referencia+"',"+hab+",'"+dnihue+"','"+dniemp+"')";
             objConectar.ejecutarBD(strSQL);
            }
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al registrar" +e.getMessage())  ;    
        }
    }
    
    public void modificarReserva(int cod,Date ini,Date fin,boolean estado, boolean confirmacion, String referencia, int hab, String dnihue, String dniemp) throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("update reserva set fechaini=?,fechafin=?,estado=?, confirmacion=?, referenciapago=?, numerohab=? dniemp=? where dnihue=? and cod=?");
            sentencia.setDate(1, (java.sql.Date) ini);
            sentencia.setDate(2, (java.sql.Date) fin);
            sentencia.setBoolean(3, estado);
            sentencia.setBoolean(4, confirmacion);
            sentencia.setString(5, referencia);
            sentencia.setInt(6, hab);
            sentencia.setString(7,dniemp);
            sentencia.setString(8, dnihue);
            sentencia.setInt(9, cod);
            sentencia.executeUpdate(); 
        } catch (Exception e) {
            throw new Exception("Error al registrar" +e.getMessage());    
        }
    }
    
    public ResultSet listarReserva() throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select* from Reserva where estado=true");
            rs = sentencia.executeQuery();
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar" +e.getMessage());
        }
    }
    
    public void darBajaReserva(int cod) throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("update reserva set estado=false where numerores=?");
            sentencia.setInt(1,cod);
            sentencia.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al dar de baja a la reserva" +e.getMessage());    
        }
    }
    
    
}
