
package capaNegocio;
import capaDatos.*;
import capaPresentacion.jfPrincipal;
import java.sql.*;

 /**
 *
 * @author ROJASB
 */
public class clsHospedaje {
    clsJDBCConexion objConectar = new clsJDBCConexion();
    //String strSQL;
    ResultSet rs = null;
    Connection con;
    Statement sent;
    
    public Integer generarNumeroHospedaje() throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select COALESCE(max(numerohos),0)+1 as numero from hospedaje");
            rs=sentencia.executeQuery();
            while(rs.next()){
                return rs.getInt("numero");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar el número de hospedaje... "+e.getMessage());
        }
        return 0;
    }
    
    public boolean registrarHospedaje(Integer num, Date fecIni,String mot,Double cos, Integer numHab, String dniHue,String dniEmp) throws Exception{
        try {
            boolean valor = false;
            boolean registrar = false;
            objConectar.conectar();
            con = objConectar.getConnection();
            con.setAutoCommit(false);
            CallableStatement sentencia1 = con.prepareCall("select vigencia from habitacion where numerohab=? and estado='D'");
            sentencia1.setInt(1, numHab);
            rs = sentencia1.executeQuery();
            while (rs.next()){
                valor = rs.getBoolean("vigencia");
            }
            if(valor){
                CallableStatement sentencia2 = con.prepareCall("insert into HOSPEDAJE values(?,?,?,?,?,true,false,?,?,?)");
                sentencia2.setInt(1, num);
                sentencia2.setDate(2, new java.sql.Date(fecIni.getTime()));
                sentencia2.setDate(3,  new java.sql.Date(fecIni.getTime()));
                sentencia2.setString(4, mot);
                sentencia2.setDouble(5, cos);
                sentencia2.setInt(6,numHab);
                sentencia2.setString(7,dniHue);
                sentencia2.setString(8,dniEmp);
                sentencia2.executeUpdate();
                con.commit();
                return true;
            }else{
                con.commit();
                return false;
            }
            
        } catch (Exception e) {
            con.rollback();
            throw new Exception ("Error al registrar Habitación!: "+e.getMessage());
            //throw new Exception (e.getMessage());
        }
    }
    
    public ResultSet listarHospedajes() throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select h.numerohos, h.numerohab, th.nombre,(hu.nombres||' '||hu.apellidos) as huesped, h.fechaini from hospedaje h inner join habitacion ha on ha.numerohab=h.numerohab inner join tipo_habitacion th on ha.codigoth=th.codigoth inner join huesped hu on h.dnihue=hu.dnihue");
            rs = sentencia.executeQuery();
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar habitaciones: "+e.getMessage());
        }
    }
    
    public ResultSet buscarHospedajes(Integer num) throws Exception{
        try {
            objConectar.conectar();
            con = objConectar.getConnection();
            CallableStatement sentencia = con.prepareCall("select * from hospedaje where numerohos=?");
            sentencia.setInt(1, num);
            rs = sentencia.executeQuery();
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar habitaciones: "+e.getMessage());
        }
    }
 
 public ResultSet DatosHospedaje (String dni) throws Exception{
    try {
        objConectar.conectar();
        con = objConectar.getConnection();
        CallableStatement sentencia = con.prepareCall("select huesped.nombres,huesped.apellidos,hospedaje.fechaini,hospedaje.fechafin-hospedaje.fechaini as dias\n" +
        "from huesped inner join hospedaje on huesped.dnihue=hospedaje.dnihue where hospedaje.dnihue=? and hospedaje.estado=true");
        sentencia.setString(1, dni);
        rs= sentencia.executeQuery();
        while(rs.next()){
            return rs;
        }
        return null;
    } catch (Exception e) {
        throw new Exception (e.getMessage());
    }   
    }  
 
 public ResultSet listarHospedajesCliente (String dni) throws Exception{
    try {
        objConectar.conectar();
        con = objConectar.getConnection();
        CallableStatement sentencia = con.prepareCall("select habitacion.numerohab,tipo_habitacion.nombre,tipo_habitacion.precio\n" +
        "from hospedaje \n" +
        "inner join habitacion on habitacion.numerohab=hospedaje.numerohab \n" +
        "inner join tipo_habitacion on habitacion.codigoth=tipo_habitacion.codigoth\n" +
        "where hospedaje.dnihue=? and hospedaje.estado=true");
        sentencia.setString(1, dni);
        rs= sentencia.executeQuery();
             return rs;
    } catch (Exception e) {
        throw new Exception ("Error al listar  los hospedajes del cliente: "+e.getMessage());
    }
       
  } 
 
 public ResultSet listarServiciosCliente (String dni) throws Exception{
    try {
        objConectar.conectar();
        con = objConectar.getConnection();
        CallableStatement sentencia = con.prepareCall("select tipo_servicio.nombre,habitacion.numerohab,servicio.costototal\n" +
        "from hospedaje \n" +
        "inner join servicio on servicio.numerohos=hospedaje.numerohos\n" +
        "inner join tipo_servicio on servicio.codigots=tipo_servicio.codigots\n" +
        "inner join habitacion on habitacion.numerohab=hospedaje.numerohab \n" +
        "inner join tipo_habitacion on habitacion.codigoth=tipo_habitacion.codigoth\n" +
        "where hospedaje.dnihue=? and hospedaje.estado=true");
        sentencia.setString(1, dni);
        rs=sentencia.executeQuery();
        return rs;
    } catch (Exception e) {
        throw new Exception (e.getMessage());
    }
       
  }
 
 public Float CostoTServiciosCliente (String dni) throws Exception{
    try {
        objConectar.conectar();
        con = objConectar.getConnection();
        CallableStatement sentencia = con.prepareCall("select SUM(servicio.costototal) as costo\n" +
        "from hospedaje \n" +
        "inner join servicio on servicio.numerohos=hospedaje.numerohos\n" +
        "inner join tipo_servicio on servicio.codigots=tipo_servicio.codigots\n" +
        "inner join habitacion on habitacion.numerohab=hospedaje.numerohab \n" +
        "inner join tipo_habitacion on habitacion.codigoth=tipo_habitacion.codigoth\n" +
        "where hospedaje.dnihue=? and hospedaje.estado=true");
        sentencia.setString(1, dni);
        rs=sentencia.executeQuery();
        while(rs.next()){
            rs.getFloat("costo");
        }
    } catch (Exception e) {
        throw new Exception (e.getMessage());
    }
        return null;
       
  }

public Float CostoTHabCliente (String dni) throws Exception{
    try {
        objConectar.conectar();
        con = objConectar.getConnection();
        CallableStatement sentencia = con.prepareCall("select SUM(tipo_habitacion.precio) as costo\n" +
        "from hospedaje \n" +
        "    inner join habitacion on habitacion.numerohab=hospedaje.numerohab \n" +
        "    inner join tipo_habitacion on habitacion.codigoth=tipo_habitacion.codigoth\n" +
        "    where hospedaje.dnihue=? and hospedaje.estado=true");
        sentencia.setString(1, dni);
        rs=sentencia.executeQuery();
        while(rs.next()){
            rs.getFloat("costo");
        }
        return null;
    } catch (Exception e) {
        throw new Exception (e.getMessage());
    }
  }

public boolean finalizarHospedaje(String dni) throws Exception{
    try {
        /*
        Transacción
        select * from hospedaje --estado, estadopago 
	select * from servicio --estado, estadopago
	select * from habitacion --estado 'D'
        */
        objConectar.conectar();
        con = objConectar.getConnection();
        con.setAutoCommit(false);
        CallableStatement sentencia1 = con.prepareCall("update habitacion set estado = 'D' where numerohab=(select numerohab from hospedaje where dnihue=? and estado=true and estadopago=false)");
        sentencia1.setString(1, dni);
        sentencia1.executeUpdate();
        CallableStatement sentencia2 = con.prepareCall("update servicio set estadopago = true, estado=false where numerohos=(select numerohos from hospedaje where dnihue='"+dni+"' and estado=true and estadopago=false)");
        sentencia2.setString(1, dni);
        sentencia2.executeUpdate();
        CallableStatement sentencia3 = con.prepareCall("update hospedaje set estadopago = true, estado=false where dnihue=? and estado=true and estadopago=false");
        sentencia3.setString(1, dni);
        sentencia3.executeUpdate();
        con.commit();
    } catch (Exception e) {
        con.rollback();
        throw new Exception (e.getMessage());
    }
        return false;
}

}
