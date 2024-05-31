// 
// Decompiled by Procyon v0.5.36
// 

package edu.eci.labinfo.controluserslogoff;

import java.util.Enumeration;
import java.net.SocketException;
import java.sql.SQLException;
import java.net.NetworkInterface;
import java.sql.Timestamp;
import java.util.Date;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.labinfo.persistencia.*;

import javax.swing.*;
import java.net.InetAddress;

public class Main
{
    public static void main(final String[] args) {
//        JPanel panel = new JPanel();
//        JButton button = new JButton("Haz clic");
//        panel.add(button);
//
//        JFrame frame = new JFrame("Mi programa");
//        frame.add(panel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 400);
//        frame.setVisible(true);
        final String user = System.getProperty("user.name");
        String host = "";
        try {
            final InetAddress addr = InetAddress.getLocalHost();
            host = addr.getHostName();
        }
        catch (UnknownHostException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            final ServiciosPersistencia sp = new ServiciosPersistencia();
            final Registro pend = sp.logedOn(host);
            final Registro root = sp.logedOnRoot(host);
            final Timestamp now = new Timestamp(new Date().getTime());
            String ip = "";
            boolean found = false;
            final Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements() && !found) {
                final NetworkInterface intf = interfaces.nextElement();
                for (Enumeration<InetAddress> addr2 = intf.getInetAddresses(); addr2.hasMoreElements() && !found;) {
                    final String tmp = addr2.nextElement().getHostAddress();
                    if (tmp.contains("10.2")) {
                        found = true;
                        ip = tmp;
                    }
                }
            }
            System.out.println("host: " + host + " IP: " + ip + " fecha: " + now);
            if (pend != null) {
                sp.close(now, ip);
            }
            if (root != null) {
                sp.closeRoot(root.getEquipo(), root.getLogOn(), now);
            }
        }
        catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | SocketException ex4) {
//            final Exception ex3;
//            final Exception ex2 = ex3;
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex4);
        }
    }
}
