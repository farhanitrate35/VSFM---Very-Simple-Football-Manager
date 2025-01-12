package server;

import sample.Database;
import sample.LoginInformation;
import sample.Main;
import sample.Player;
import util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;
    List<LoginInformation> loginCredentials = LoginInformation.presentClubs();
    Database DB = new Database();
    Server() throws Exception {
        DB.makeMainDatabase();
        DB.makeMarketDatabase();
        DB.ClubList = Database.ListClubs();
        try {
            serverSocket = new ServerSocket(45454);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client accepted!");
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("server.Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ReadThreadServer(networkUtil, this);

    }

    public String loginChecker(LoginInformation logInfo) {
        for(LoginInformation x : loginCredentials){
            if((x.getUsername().equals(logInfo.getUsername())) && (x.getPassword().equals(logInfo.getPassword()))) {
                return "accepted";
            }
        }
        return "denied";
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();

    }

}
