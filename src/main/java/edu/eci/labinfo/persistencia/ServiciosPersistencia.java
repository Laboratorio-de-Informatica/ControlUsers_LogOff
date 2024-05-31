// 
// Decompiled by Procyon v0.5.36
// 

package edu.eci.labinfo.persistencia;
import edu.eci.labinfo.controluserslogoff.*;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class ServiciosPersistencia
{
    public Registro logedOn(final String equipo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        final Connection con = Conexion.conectar();
        final PreparedStatement ps = con.prepareStatement("select estudiante,equipo,logon,ip from datos where equipo = ? and logoff is null;");
        ps.setString(1, equipo);
        final ResultSet result = ps.executeQuery();
        Registro reg = null;
        if (result.next()) {
            reg = new Registro(result.getString(2), result.getString(1), result.getTimestamp(3), result.getString(4));
        }
        con.close();
        ps.close();
        return reg;
    }
    
    public Registro logedOnRoot(final String equipo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        final Connection con = Conexion.conectar();
        final PreparedStatement ps = con.prepareStatement("select equipo,logon from root_logs where equipo = ? and logoff is null;");
        ps.setString(1, equipo);
        final ResultSet result = ps.executeQuery();
        Registro reg = null;
        if (result.next()) {
            reg = new Registro(result.getString(1), result.getTimestamp(2));
        }
        con.close();
        ps.close();
        return reg;
    }
    
    public Integer insertar(final String carne, final String equipo, final Timestamp fechaLogon, final Timestamp fechaLogoff, final String ip) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        int i = 0;
        final Connection con = Conexion.conectar();
        PreparedStatement ps = null;
        ps = con.prepareStatement("insert into datos (estudiante,equipo,logon,logoff,ip) values (?, ?, ?, ?,?) ;");
        ps.setString(1, carne);
        ps.setString(2, equipo);
        ps.setTimestamp(3, fechaLogon);
        if (fechaLogoff != null) {
            ps.setTimestamp(4, fechaLogoff);
        }
        else {
            ps.setTimestamp(4, null);
        }
        ps.setString(5, ip);
        i = ps.executeUpdate();
        con.close();
        ps.close();
        return i;
    }
    
    public Integer insertarRoot(final String equipo, final Timestamp fechaLogon, final Timestamp fechaLogoff, final String ip) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        int i = 0;
        final Connection con = Conexion.conectar();
        PreparedStatement ps = null;
        ps = con.prepareStatement("insert into root_logs (equipo,logon,logoff) values (?, ?, ?) ;");
        ps.setString(1, equipo);
        ps.setTimestamp(2, fechaLogon);
        if (fechaLogoff != null) {
            ps.setTimestamp(3, fechaLogoff);
        }
        else {
            ps.setTimestamp(3, null);
        }
        ps.setString(4, ip);
        i = ps.executeUpdate();
        con.close();
        ps.close();
        return i;
    }
    
    public Integer closeRegister(final String carne, final String equipo, final Timestamp fechaLogon, final Timestamp fechaLogoff, final String ip) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        int i = 0;
        final Connection con = Conexion.conectar();
        PreparedStatement ps = null;
        ps = con.prepareStatement("update datos set logoff = ? where estudiante = ? and equipo = ? and logon = ? and ip = ? ;");
        ps.setString(2, carne);
        ps.setString(3, equipo);
        ps.setTimestamp(4, fechaLogon);
        ps.setTimestamp(1, fechaLogoff);
        ps.setString(5, ip);
        i = ps.executeUpdate();
        con.close();
        ps.close();
        return i;
    }
    
    public Integer closeRoot(final String equipo, final Timestamp fechaLogon, final Timestamp fechaLogoff) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        int i = 0;
        final Connection con = Conexion.conectar();
        PreparedStatement ps = null;
        ps = con.prepareStatement("update root_logs set logoff = ? where equipo = ? and logon = ? ;");
        ps.setString(2, equipo);
        ps.setTimestamp(3, fechaLogon);
        ps.setTimestamp(1, fechaLogoff);
        i = ps.executeUpdate();
        con.close();
        ps.close();
        return i;
    }
    
    public Integer close(final Timestamp fechaLogoff, final String ip) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        int i = 0;
        final Connection con = Conexion.conectar();
        PreparedStatement ps = null;
        ps = con.prepareStatement("update datos set logoff = ? where ip = ? and logoff is null;");
        ps.setTimestamp(1, fechaLogoff);
        ps.setString(2, ip);
        i = ps.executeUpdate();
        con.close();
        ps.close();
        return i;
    }
}
