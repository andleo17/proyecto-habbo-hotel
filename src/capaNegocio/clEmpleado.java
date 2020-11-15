/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaNegocio;


import capaDatos.*;
import java.sql.*;
/**
 * 
 */
/**
 *
 * @author ROJASB
 */
public class clEmpleado {
 
    clsJDBCConexion objConectar = new clsJDBCConexion();
    String strSQL;
    ResultSet rs = null;
    Connection con;
    Statement sent; 
    
     public ResultSet buscarEmpleado(String dni) throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            strSQL = "select * from empleado where dniemp='" + dni+"'";
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar Empleado: "+e.getMessage());
        }
    }    
    
    public void registrarEmple(String dni, String nom, String ape,String fecha,String sex,String dir,String tel, boolean vig) throws Exception{
        
        Boolean s;
        if(sex.equalsIgnoreCase("Masculino")){
            s=true;
        }else{
            s=false;
        }
        
        Boolean valor;

        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            con.setAutoCommit(false);
            strSQL = "select * from empleado where dniemp='"+dni+"'";
            rs = objConectar.consultarBD(strSQL);
            if(rs.next()){
                valor = true;
            }else{
                valor = false;
            }
            
            if(valor){
                throw new Exception("Empleado ya existe");
            }else{
                //El empleado no existe, se puede registrar 
                strSQL = "insert into empleado values ('"+dni+"','"+nom+"','"+ape+"','"+fecha+"','"+s+"','"+dir+"','"+tel+"','"+vig+"')";
                objConectar.ejecutarBD(strSQL);
            }
            
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al agregar un empleado: "+e.getMessage());
        }
    }
       
     public Boolean verificarEmplea(String dni) throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            strSQL = "select * from empleado where dniemp='"+dni+"' and vigencia=true";
            rs=objConectar.consultarBD(strSQL);
            if(rs.next()){
                return false;
                
            } else{
                return true;
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar datos del empleado: "+e.getMessage());
        }
    }
     
    public void modificarEmpleado(String dni, String nom , String ape , String fecha, String sex,String dire , String tel , Boolean vig) throws Exception{
        /*
        create or replace function ModificarEmpleado
        (dni varchar(8),nom varchar(30) , ape varchar(60) , fecha date, sex Boolean , dire varchar(50) , tel varchar(11), vig Boolean) returns void as
        $$
        declare
        begin
            update empleado set nombres=nom,apellidos=ape,fechanac=fecha,sexo=sex,direccion=dire,telefono=tel,vigencia=vig where dniemp=dni;
        end;
        $$language'plpgsql'
        */
        boolean s;
        if(sex.equalsIgnoreCase("Masculino")){
            s=true;
        }else{
            s=false;
        }
        try {
            //strSQL = "update empleado set nombres='"+nom+"',apellidos='"+ape+"',fechanac='"+fecha+"',sexo='"+s+"',direccion='"+dire+"',telefono='"+tel+"',vigencia='"+vig+"' where dniemp='"+dni+"'";
            strSQL = "select ModificarEmpleado('"+dni+"','"+nom+"','"+ape+"','"+fecha+"','"+s+"','"+dire+"','"+tel+"','"+vig+"');";
            objConectar.conectar();
            con = objConectar.getConnection();
            objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar un empleado: "+e.getMessage());
        }
}
    
    public void bajaEmpleado(String dni) throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            con.setAutoCommit(false);
            CallableStatement sentencia = con.prepareCall("update empleado set vigencia = false where dniemp =?");
            sentencia.setString(1, dni);
            sentencia.executeUpdate();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al dar de baja al empleado: "+e.getMessage());
        }
    }
    
     public Boolean validarVigenciaEmp(String dni) throws Exception{
        strSQL="select vigencia from empleado where dniemp='"+dni+"'";
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select vigencia from empleado where dniemp=?");
            sentencia.setString(1, dni);
            rs=sentencia.executeQuery();
            if(rs.next()){
                return rs.getBoolean("vigencia");   
            }
        } catch (Exception e) {
            throw new Exception("Error al validar Vigencia: "+e.getMessage());
        }
        return false;
    }
     
     public void eliminarEmple(String dni) throws Exception{
        //Transaccion al eliminar empleado
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            con.setAutoCommit(false);
            strSQL="select count(*) as operaciones from empleado e inner join hospedaje h on e.dniemp=h.dniemp inner join reserva r on r.dniemp=e.dniemp where e.dniemp='"+dni+"'";
            rs=objConectar.consultarBD(strSQL);
            rs.next();
            if(rs.getInt("operaciones") > 0){
                strSQL="update empleado set vigencia=false where dniemp='" +dni+"'";
            }else{
              strSQL="delete from empleado where dniemp='"+dni+"'";
            }
            objConectar.ejecutarBD(strSQL);
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al eliminar al empleado; "+e.getMessage());
        }
    }
     
      public ResultSet listarEmpleados() throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            strSQL = "select * from empleado";
            rs=objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar empleados: "+e.getMessage());
        }
    }
      
      public Integer totalEmpleados() throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            strSQL = "select count(*) as total from empleado";
            rs=objConectar.consultarBD(strSQL);
            if(rs.next()){
                return rs.getInt("total");
            }
        } catch (Exception e) {
            throw new Exception("Error al calular el total de empleados: "+e.getMessage());
        }
        return 0;
    }
}
