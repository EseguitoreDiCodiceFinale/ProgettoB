package org.example;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ServerMain {
    public static void main(String[] args) {
        try {
            new ServerImpl().run();

        } catch (RemoteException e) {
            System.out.println("Errore Server");
       /* }catch (SQLException e){
            System.out.println("Errore Database");
        }*/
        }
    }
}