

package capaNegocio;
import capaDatos.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * @author ROJASB
 */
public class clsHuesped {
    clsJDBCConexion objConectar = new clsJDBCConexion();
    //String strSQL;
    ResultSet rs = null;
    Connection con;
    Statement sent;
    
    
   public ResultSet buscarHuesped(String dni ) throws Exception { 
         try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select * from huesped where dnihue=?");
            sentencia.setString(1, dni);
            rs= sentencia.executeQuery();
            return rs;      
        } catch (Exception e) {
            throw new  Exception ("error al buscar huesped: "+e.getMessage());
        }
    }
    
    public void RegistrarHuesped(String dni, String nomb, String apell, String ciu, String tlf, String corr, boolean est) throws Exception{
        try {
            boolean valor = false;
            objConectar.conectar();
            con = objConectar.getConnection();
            con.setAutoCommit(false);
            CallableStatement sentencia1 = con.prepareCall("select * from huesped where dnihue=?");
            sentencia1.setString(1, dni);
            rs= sentencia1.executeQuery();
            while(rs.next()){
                valor=true;
            }
            if(!valor){
                CallableStatement sentencia = con.prepareCall("insert into huesped values(?,?,?,?,?,?,?)");
                sentencia.setString(1, dni);
                sentencia.setString(2, nomb);
                sentencia.setString(3, apell);
                sentencia.setString(4, ciu);
                sentencia.setString(5, tlf);
                sentencia.setString(6, corr);
                sentencia.setBoolean(7,est);
                sentencia.executeUpdate();
            }
            con.commit();
        }catch (Exception e){
            con.rollback();
            throw new Exception ("Error al registrar huesped: "+e.getMessage());
        }
    }
    
    public void ModificarHuesped(String dni, String nomb, String apell, String ciu,String tlf, String corr, Boolean est) throws Exception{
        try{
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("update huesped set nombres=?,apellidos=?,ciudad=?, telefono=?',correo=?,estado=? where dnihue=?");
            sentencia.setString(1, nomb);
            sentencia.setString(2, apell);
            sentencia.setString(3, ciu);
            sentencia.setString(4, tlf);
            sentencia.setString(5, corr);
            sentencia.setBoolean(6,est);
            sentencia.setString(7, dni);
            sentencia.executeUpdate();
        }catch (Exception e){
            throw new Exception ("Error al modificar datos de huesped: "+e.getMessage());
        }
    }
    
    public void DarBajaHuesped(String dni)throws Exception{
        try{
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("update huesped set estado=false where dnihue=?");
            sentencia.setString(1, dni);
            sentencia.executeUpdate();
        }catch(Exception e){
            throw new Exception("Error al dar de baja al huesped: "+e.getMessage());
        }
    }
    
    public ResultSet listarHuesped() throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select * from Huesped");
            rs = sentencia.executeQuery();
            return rs;
        } catch (Exception e) {
            throw new Exception("Error a listar datos de huesped: "+e.getMessage());
        }
    }
    
     public Boolean verificaHuesped( String dni) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select * from huesped where dnihue=?");
            sentencia.setString(1, dni);
            rs= sentencia.executeQuery();
            if( rs.next()){ 
                return false ; // quiere decir que al encontrar el nro de dni del huesped retornaria false
            } else {
                return true; // quiere decir que no encontro por lo que si puede registrar
            }
        } catch (Exception e) {
            
            throw new Exception ("error al verificar huesped: "+e.getMessage());
        }
        
    }
     
      public Integer totalEmpleados() throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select count(*) as total from huesped");
            rs=sentencia.executeQuery();
            if(rs.next()){
                return rs.getInt("total");
            }
        } catch (Exception e) {
            throw new Exception("Error al calular el total de empleados: "+e.getMessage());
        }
        return 0;
    }
}
