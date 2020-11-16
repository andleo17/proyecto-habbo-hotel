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

/**
 *
 * @author ROJASB
 */
public class clsServicio {
    
    clsJDBCConexion objConectar = new clsJDBCConexion();
    //String strSQL;
    ResultSet rs = null;
    Connection con;
    Statement sent;
    
    public boolean consultarHospedaje (String dni) throws Exception{
        //Verificamos si el cliente tiene una reserva vigente para poder registrar, modificar o darle de baja a uno de sus servicios 
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select * from hospedaje where dnihue=? and estado=true");
            sentencia.setString(1, dni);
            rs= sentencia.executeQuery();
            if( rs.next()){ 
                return true;
            }else{
                return false;
            } 
        } catch (Exception e) {
            
            throw new Exception ("Error al buscar o el cliente no tiene hospedajes vigentes" +e.getMessage());
        }finally {
            con.close();
        }
    }
    
    public ResultSet listarCliente(String dni) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select nombres,apellidos from huesped  where dnihue=? and estado=true");
            sentencia.setString(1, dni);
            rs= sentencia.executeQuery();
            return rs;
        } catch (Exception e) {
            
            throw new Exception ("Error al listar" +e.getMessage());
        }finally {
            con.close();
        }
    }
    
    public ResultSet listarHabitacionesCliente(String dni) throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select numerohab from hospedaje  where dnihue=? and estado=true");
            sentencia.setString(1, dni);
            rs= sentencia.executeQuery();
            if( rs.next()){ 
                return rs;
            }
        } catch (Exception e) {
            
            throw new Exception ("Error al buscar habitaciones" +e.getMessage());
        }finally {
            con.close();
        }
        return null;
    }
    
    public void registarServicio(String descripcion,float costo, int tipo, int hospedaje) throws Exception{
        try {
            boolean valor;
            objConectar.conectar();
            con = objConectar.getConnection();
            con.setAutoCommit(false);
            CallableStatement sentencia1 = con.prepareCall("select * from servicio where descripcion=?");
            sentencia1.setString(1, descripcion);
            sentencia1.executeQuery();
            if(rs.next()){
                valor = false; // la descripcion de ese servicio ya existe por eso le ponemos false
            }else{
                valor = true;
            }
             
            if(valor){
                CallableStatement sentencia = con.prepareCall("insert into servicio values((select COALESCE(max (codigoser),0)+1 as codigo from servicio),current_date,?,?,true,false,?,?)");
                sentencia.setString(1, descripcion);
                sentencia.setFloat(2, costo);
                sentencia.setInt(3, tipo);
                sentencia.setInt(4, hospedaje);
                sentencia.executeUpdate();
            }
            
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al registrar" +e.getMessage())  ;    
        }finally {
            con.close();
        }
    }
    
    public void modificarServicio(int cod,String descripcion,float costo, int tipo) throws Exception{
        /*
        create or replace function ModificarServicio(cod int, des varchar (100), costo float, tipo int ) returns void as
        $$
        declare
        begin
            update servicio set descripcion=des, costototal=costo,codigots=tipo where codigoser=cod;
        end;
        $$language'plpgsql'
        */
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            //update servicio set descripcion=?, costototal=?,codigots=? where codigoser=?
            CallableStatement sentencia = con.prepareCall("{call ModificarServicio(?,?,?,?)}");
            sentencia.setInt(1, cod);
            sentencia.setString(2, descripcion);
            sentencia.setFloat(3, costo);
            sentencia.setInt(4, tipo);
            sentencia.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al registrar" +e.getMessage());    
        }finally {
            con.close();
        }
    }
    
    public ResultSet listarServicio() throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select* from servicio");
            rs = sentencia.executeQuery();
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar el servicio" +e.getMessage());
        }finally {
            con.close();
        }
    }
    
    public void darBajaServicio(int cod) throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("update servicio set estado=false where codigoser=?");
            sentencia.setInt(1, cod);
            sentencia.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al dar de baja al servicio" +e.getMessage());    
        }finally {
            con.close();
        }
    }
    
    public ResultSet listarServicioVigente() throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select* from servicio where vigencia=true");
            rs = sentencia.executeQuery();
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar el tipo de servicio" +e.getMessage());
        }finally {
            con.close();
        }
        
    }
    
    public ResultSet listarTipoServicio() throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select* from tipo_servicio where vigencia=true");
            rs = sentencia.executeQuery();
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar el tipo de servicio" +e.getMessage());
        }finally {
            con.close();
        }
    }
    
    public ResultSet listarServicioCliente(String dni) throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select servicio.codigoser,habitacion.numerohab,hospedaje.dnihue,tipo_servicio.nombre, servicio.descripcion,servicio.fecha,tipo_servicio.codigots,servicio.costototal from servicio \n" +
            "inner join tipo_servicio on tipo_servicio.codigots=servicio.codigots\n" +
            "inner join hospedaje on servicio.numerohos=hospedaje.numerohos \n" +
            "inner join habitacion on hospedaje.numerohab=habitacion.numerohab\n" +
            "where hospedaje.dnihue=? and hospedaje.estado=true and servicio.estado=true");
            sentencia.setString(1, dni);
            rs = sentencia.executeQuery();
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar los servicios" +e.getMessage());
        }finally {
            con.close();
        }
    }
    
    public ResultSet listarServicios() throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select servicio.codigoser,habitacion.numerohab,hospedaje.dnihue,tipo_servicio.nombre, servicio.descripcion,servicio.fecha,tipo_servicio.codigots from servicio \n" +
            "inner join tipo_servicio on tipo_servicio.codigots=servicio.codigots\n" +
            "inner join hospedaje on servicio.numerohos=hospedaje.numerohos \n" +
            "inner join habitacion on hospedaje.numerohab=habitacion.numerohab\n" +
            "where hospedaje.estado=true");
            rs = sentencia.executeQuery();
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar los servicios" +e.getMessage());
        }finally {
            con.close();
        }
    }
    
    
    public float PrecioTipoServicio(String nom) throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select precio from tipo_servicio where nombre=?");
            sentencia.setString(1, nom);
            rs= sentencia.executeQuery();
            while (rs.next()){
               return rs.getFloat("precio");
            }
            return 0;
        } catch (Exception e) {
            throw new Exception("Error" +e.getMessage());
        }finally {
            con.close();
        }
    }
    
    public int buscartipo(String nom) throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select codigots from tipo_servicio where nombre=?");
            sentencia.setString(1, nom);
            rs = sentencia.executeQuery();
            while (rs.next()){
               return rs.getInt("codigots");
            }
            return 0;
        } catch (Exception e) {
            throw new Exception("Error al listar los servicios" +e.getMessage());
        }finally {
            con.close();
        }
    }
    
    
    public int buscarHospedaje(int habitacion,String dni ) throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select numerohos from hospedaje where numerohab=? and dnihue=? and estado=true");
            sentencia.setInt(1, habitacion);
            sentencia.setString(2, dni);
            rs = sentencia.executeQuery();
            while (rs.next()){
               return rs.getInt("numerohos");
            }
            return 0;
        } catch (Exception e) {
            throw new Exception("Error al listar los servicios" +e.getMessage());
        }finally {
            con.close();
        }
    }
}


