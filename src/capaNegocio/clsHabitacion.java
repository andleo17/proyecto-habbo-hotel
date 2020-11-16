
package capaNegocio;
import capaDatos.*;
import java.sql.*;

/**
 *
 * @author ROJASB
 */
public class clsHabitacion {
    clsJDBCConexion objConectar = new clsJDBCConexion();
    //String strSQL;
    ResultSet rs = null;
    Connection con;
    Statement sent;
    
   //metodo para verificar que no este el nro de habi  
    public Boolean verificanumhab( Integer num) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia1 = con.prepareCall("select * from tipo_servicio where codigots=?");
            sentencia1.setInt(1, num);
            rs=sentencia1.executeQuery();
            if( rs.next()){ 
                return false ; // quiere decir que al encontrar el nro de hab retornaria false
            } else {
                return true; // quiere decir que no encontro por lo que si puede registrar
            }
        } catch (Exception e) {
            throw new Exception ("error al verificar servicio" +e.getMessage());
        }
        
    }
    
    public void registarHabit(Integer num , String desc, String esta, Boolean vig ,  Integer codth) throws Exception{
        try {
            Boolean valor;
            objConectar.conectar();
            con = objConectar.getConnection();
            con.setAutoCommit(false);
            CallableStatement sentencia1 = con.prepareCall("select * from habitacion where numerohab=?");
            sentencia1.setInt(1, num);
            rs=sentencia1.executeQuery();
            if(rs.next()){
                valor=false;
            }else{
                valor=true;
            }
            
            if(valor){
                CallableStatement sentencia = con.prepareCall("insert into habitacion values(?,?,?,?,?)");
                sentencia.setInt(1, num);
                sentencia.setString(2, desc);
                sentencia.setString(3,esta);
                sentencia.setBoolean(4, vig);
                sentencia.setInt(5,codth);
                sentencia.executeUpdate();   
            }
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al registrar" +e.getMessage())  ;    
        }
        
    }
    
    public void modificarHabit(Integer num, String desc, String esta, Boolean vig, Integer codth) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("update habitacion set descripcion=?,estado=?,vigencia=?,codigoth=? where numerohab=?");
                
                sentencia.setString(1, desc);
                sentencia.setString(2,esta);
                sentencia.setBoolean(3, vig);
                sentencia.setInt(4,codth);
                sentencia.setInt(5, num);
                sentencia.executeUpdate();  
        } catch (Exception e) {
            throw e;
        }
    }
    
    public ResultSet listarHab() throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select h.numerohab,h.descripcion,h.estado,h.vigencia,th.nombre,th.precio from habitacion as h\n" +
                "inner join tipo_habitacion as th on h.codigoth=th.codigoth");
            rs= sentencia.executeQuery();
            return rs;
        } catch (Exception e) {
            throw new Exception("ERROS AL LISTAR " +e.getMessage());
        }
    }
    
    public ResultSet buscarHabi(Integer  num ) throws Exception { 
         try {
             objConectar.conectar();
             con = objConectar.getConnection();
             CallableStatement sentencia = con.prepareCall("select * from habitacion where numerohab=?");
             sentencia.setInt(1, num);
             rs= sentencia.executeQuery();
            return rs;       
        } catch (Exception e) {
            throw new  Exception ("error al buscar w" +e.getMessage());
        }
    }
    
    public ResultSet consultarHabitacion(Integer numH) throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select h.descripcion,h.estado,h.vigencia,th.nombre from habitacion as h inner join tipo_habitacion as th on h.codigoth=th.codigoth where h.numerohab=?");
            sentencia.setInt(1, numH);
            rs= sentencia.executeQuery();
            return rs; 
        } catch (Exception e) {
            throw new Exception("Error al consultar Habitación" +e.getMessage());
        }
    }
    
    public void eliminarhabitacion(Integer num) throws Exception {
        try { //transaccion
            objConectar.conectar();
            con = objConectar.getConnection();
            con.setAutoCommit(false);
            CallableStatement sentencia1 = con.prepareCall("delete from hospedaje where numerohab=?");
            sentencia1.setInt(1, num);
            sentencia1.executeUpdate();
            CallableStatement sentencia2 = con.prepareCall("delete from reserva where numerohab=?");
            sentencia2.setInt(1, num);
            sentencia2.executeUpdate();
            CallableStatement sentencia3 = con.prepareCall("delete from habitacion where numerohab=?");
            sentencia3.setInt(1, num);
            sentencia3.executeUpdate();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error cuando eliminas la habitacion " +e.getMessage());
        }
    }
    
     public void dardebajahabitacion(Integer num) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia1 = con.prepareCall("update habitacion set vigencia=false where numerohab=?");
            sentencia1.setInt(1, num);
            sentencia1.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error cuando eliminas la habitacion " +e.getMessage());
        }
    }
     
       public ResultSet listarHabDis(String nombrehab) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia1 = con.prepareCall("select numerohab,nombre,precio from habitacion h inner join tipo_habitacion th on h.codigoth=th.codigoth where estado ='D' and nombre=? and h.vigencia=true");
            sentencia1.setString(1, nombrehab);
            rs=sentencia1.executeQuery();
            return rs;
        } catch (Exception e) {
            throw new Exception("ERROS AL LISTAR HAB DISPONIBLES " +e.getMessage());
        }
    }
       
    public ResultSet listarHabporEstadoTipo(String tipo, String estado) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia1 = con.prepareCall("select h.numerohab,h.descripcion,th.precio from habitacion as h inner join tipo_habitacion as th on h.codigoth=th.codigoth where h.estado =? and th.nombre=?");
            sentencia1.setString(1, estado);
            sentencia1.setString(2, tipo);
            rs= sentencia1.executeQuery();
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar" +e.getMessage());
        }
    }  
    
     public void cambiarEstadoHabitacion(Integer num,String est) throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia1 = con.prepareCall("update HABITACION set estado=? where numerohab=?");
            sentencia1.setString(1, est);
            sentencia1.setInt(2, num);
            sentencia1.executeUpdate();
        } catch (Exception e) {
            throw new Exception ("Error al cambiar estado de Habitación!: "+e.getMessage());
        }
    }
     
     public String obtenerNombreTipoHabitacion(Integer num) throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia1 = con.prepareCall("select th.nombre from habitacion h inner join tipo_habitacion th on h.codigoth=th.codigoth where numerohab=?");
            sentencia1.setInt(1, num);
            rs=sentencia1.executeQuery();
            if(rs.next()){
                return rs.getString("nombre");
            }
        } catch (Exception e) {
            throw new Exception("Error al realizar consulta...: "+e.getMessage());
        }
        return null;
    }

}
