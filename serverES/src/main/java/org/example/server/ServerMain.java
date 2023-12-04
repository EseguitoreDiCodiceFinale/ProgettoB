package org.example.server;

import java.rmi.RemoteException;

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