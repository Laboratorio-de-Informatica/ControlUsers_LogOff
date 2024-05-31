// 
// Decompiled by Procyon v0.5.36
// 

package edu.eci.labinfo.controluserslogoff;

import java.sql.Timestamp;

public class Registro
{
    private String equipo;
    private String usuario;
    private Timestamp logOn;
    private Timestamp logOff;
    private String ip;
    
    public Registro() {
    }
    
    public Registro(final String equipo, final String user, final Timestamp logOn, final String ip) {
        this.equipo = equipo;
        this.usuario = user;
        this.logOn = logOn;
        this.ip = ip;
    }
    
    public Registro(final String equipo, final Timestamp logOn) {
        this.equipo = equipo;
        this.logOn = logOn;
    }
    
    @Override
    public String toString() {
        return this.equipo + " " + this.ip + " " + this.usuario + " " + this.logOn + " " + this.logOff;
    }
    
    public String getEquipo() {
        return this.equipo;
    }
    
    public void setEquipo(final String equipo) {
        this.equipo = equipo;
    }
    
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(final String usuario) {
        this.usuario = usuario;
    }
    
    public Timestamp getLogOn() {
        return this.logOn;
    }
    
    public void setLogOn(final Timestamp logOn) {
        this.logOn = logOn;
    }
    
    public Timestamp getLogOff() {
        return this.logOff;
    }
    
    public void setLogOff(final Timestamp logOff) {
        this.logOff = logOff;
    }
    
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(final String ip) {
        this.ip = ip;
    }
}
