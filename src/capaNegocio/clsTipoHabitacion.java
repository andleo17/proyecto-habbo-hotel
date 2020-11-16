
package capaNegocio;
 
import capaDatos.clsJDBCConexion;
import java.sql.*;

/**
 *
 * @author ARNOLD fecha 28/10
 */
public class clsTipoHabitacion {
    clsJDBCConexion objConectar = new clsJDBCConexion();
    //String strSQL;
    ResultSet rs = null;
    Connection con;
    Statement sent;
    
    public Integer generarCodigoTipoHabitacion() throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select COALESCE(max (codigoTH),0)+1 as codigo from tipo_habitacion");
            rs= sentencia.executeQuery();
            while(rs.next()){
                return rs.getInt("codigo"); //el codigo es del alias del CODIGO FROM
            }
        } catch (Exception e) {
            throw  new Exception("Error al generar en new codigo: "+e.getMessage());
        }
        return 0 ; 
}
    
    
    public void registrarhabit(Integer cod, String nom ,String des , Double prec, Boolean vig ) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("insert into tipo_habitacion values (? ,?,?,?,?)");
            sentencia.setInt(1, cod);
            sentencia.setString(2, nom);
            sentencia.setString(3, des);
            sentencia.setDouble(4, prec);
            sentencia.setBoolean(5, vig);
            sentencia.executeUpdate();
        } catch (Exception e) {
            throw  new Exception("Error al registrar: "+e.getMessage() ) ;
        }
    }
    
    public ResultSet buscarTipoHab ( Integer cod) throws Exception { 
          try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select * from tipo_habitacion where codigoth=?");
            sentencia.setInt(1, cod);
            rs = sentencia.executeQuery();
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar el tipo de habi: "+e.getMessage());
        }
    
}
    public void eliminarTipoHab (Integer cod) throws Exception { 
        try { //transaccion de eliminar el tipo de habitacion
            objConectar.conectar();
            con = objConectar.getConnection();
            con.setAutoCommit(false);
            CallableStatement sentencia1 = con.prepareCall("delete from habitacion where codigoth=?");
            sentencia1.setInt(1, cod);
            sentencia1.executeUpdate();
            CallableStatement sentencia2 = con.prepareCall("delete from hospedaje where numerohab=(select numerohab from habitacion where codigoth=?)");
            sentencia2.setInt(1, cod);
            sentencia2.executeUpdate();
            CallableStatement sentencia3 = con.prepareCall("delete from reserva where numerohab=(select numerohab from habitacion where codigoth=?)");
            sentencia3.setInt(1, cod);
            sentencia3.executeUpdate();
            CallableStatement sentencia4 = con.prepareCall("delete from servicio where numerohos=(select numerohos from hospedaje where numerohab=(select numerohab from habitacion where codigoth=?))");
            sentencia4.setInt(1, cod);
            sentencia4.executeUpdate();
            CallableStatement sentencia = con.prepareCall("delete from tipo_habitacion where codigoth=?");
            sentencia.setInt(1, cod);
            sentencia.executeUpdate();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error: "+e.getMessage());
        }
    }
    
    public void modificartipohab(Integer cod, String nom ,String des , double prec, Boolean vig ) throws Exception {
        /*
        create or replace function ModificarTipoHabitacion(cod int, nom varchar(30), des varchar(100) , prec float, vig Boolean ) returns void as
        $$
        declare
        begin
            update tipo_habitacion set nombre=nom, descripcion=des,precio=prec, vigencia=vig where codigoth=cod;
        end;
        $$language'plpgsql'
        */
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            //update tipo_habitacion set nombre=?, descripcion=?,precio=?, vigencia=? where codigoth=?
            CallableStatement sentencia = con.prepareCall("{call ModificarTipoHabitacion(?,?,?,?,?)}");
            sentencia.setInt(1, cod);
            sentencia.setString(2, nom);
            sentencia.setString(3, des);
            sentencia.setDouble(4, prec);
            sentencia.setBoolean(5, vig);
            sentencia.executeUpdate();
        } catch (Exception e) {
            throw  new Exception("Error al modificar tipo de habitación: "+e.getMessage()) ;
        }
    }
    
    public void dardebajatipohab(Integer cod ) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("update tipo_habitacion set vigencia= false where codigoth=?");
            sentencia.setInt(1, cod);
            sentencia.executeUpdate();
        } catch (Exception e) {
            throw  new Exception("Error al dar de bajita al tipo de habitación: "+e.getMessage() ) ;
        }
    }
    
    public ResultSet listartipohab() throws Exception { 
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select * from tipo_habitacion");
            rs= sentencia.executeQuery();
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar: "+e.getMessage());
        }
    }
    
    public ResultSet listartipohabcbo(int num) throws Exception { 
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select tipo_habitacion.nombre from tipo_habitacion inner join habitacion on habitacion.codigoth=tipo_habitacion.codigoth where numerohab=? ");
            sentencia.setInt(1, num);
            rs= sentencia.executeQuery();
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar: "+e.getMessage());
        }
    }
    
     public Integer totalTipoHabitacion() throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select count(*) as total  from tipo_habitacion");
            rs= sentencia.executeQuery();
            while(rs.next()){
                return rs.getInt("total"); //el codigo es del alias del CODIGO FROM
            }
        } catch (Exception e) {
            throw  new Exception("Error al contar: "+e.getMessage());
        }
        return 0 ; 
}
     
     public ResultSet listarTiphabvigente () throws Exception  {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select th.nombre from tipo_habitacion th inner join habitacion h on th.codigoth = h.codigoth where th.vigencia= true and h.estado='D' and h.vigencia=true group by 1 order by nombre asc");
            rs = sentencia.executeQuery();
            return rs ; //el codigo es del alias del CODIGO FROM
        } catch (Exception e) {
            throw  new Exception("Error al contar: "+e.getMessage());
        }
     }
     
   public Integer retornarCodigoTipoHabitacion(String nom ) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select codigoth from tipo_habitacion where nombre=?");
            sentencia.setString(1, nom);
            rs=sentencia.executeQuery();
            while(rs.next()){
                return rs.getInt("codigoth");
            }
        } catch (Exception e) {
            throw  new Exception("Error: "+e.getMessage());
        }
        return 0 ; 
}
   
   public Boolean validarhabportip ( Integer codth) throws Exception{
       try {
           objConectar.conectar();
           con = objConectar.getConnection();
           CallableStatement sentencia = con.prepareCall("select * from habitacion where codigoth=?");
           sentencia.setInt(1, codth);
           rs=sentencia.executeQuery();
           if (rs.next()){
               return false; // cuando si  hay habitacion de este tipo
           }else { 
               return true ; 
           }
           
       } catch (Exception e) {
           throw new Exception("Error al validar " +e.getMessage());
       } 
   }
   
   public ResultSet listarHabDis(String nombrehab) throws Exception {
        try {
           objConectar.conectar();
           con = objConectar.getConnection();
           PreparedStatement ps = con.prepareStatement("select count(*) as num_hab from habitacion h inner join tipo_habitacion th on h.codigoth=th.codigoth where h.estado ='D' and th.nombre=?");
           ps.setString(1, nombrehab);
           ResultSet rs1 = ps.executeQuery();
           rs1.next();
           if (rs1.getInt("num_hab") > 0) {
               CallableStatement sentencia = con.prepareCall("select numerohab from habitacion h inner join tipo_habitacion th on h.codigoth=th.codigoth where h.estado ='D' and th.nombre=?");
                sentencia.setString(1, nombrehab);
                return sentencia.executeQuery();
           } else {
               return null;
           }
           
        } catch (Exception e) {
            throw new Exception("Error al listar " +e.getMessage());
        }
    }
   
   public String BuscarTipoxHab (int num) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select th.nombre from habitacion h inner join tipo_habitacion th on h.codigoth=th.codigoth where h.numerohab=?");
            sentencia.setInt(1, num);
            rs= sentencia.executeQuery();
            return rs.getString("nombre");        
        } catch (Exception e) {
            throw new Exception("Error al listar " +e.getMessage());
        }
    }
}