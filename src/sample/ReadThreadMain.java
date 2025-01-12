package sample;

import server.ReadThreadServer;
import util.NetworkUtil;

import java.io.IOException;

public class ReadThreadMain implements Runnable{
    private Main main;
    private Thread thr;
    private NetworkUtil networkUtil;

    public ReadThreadMain(NetworkUtil networkUtil, Main main) {
        this.main = main;
        this.networkUtil = networkUtil;
        thr = new Thread(this);
        thr.start();
    }

    @Override
    public void run() {

        try {
            while (true) {
                Object o = networkUtil.read();
                if (o instanceof RespondMarketDatabase) {
                    // Market database response
                    RespondMarketDatabase respondMarketDatabase = (RespondMarketDatabase) o;
                    main.transferMarket = respondMarketDatabase.getMarketPlayers();
                    System.out.println("Transfer Market Obtained!!");
                    main.flag = true;
                    // notifyAll();
                }
                else if(o instanceof BuyPlayerRequest) {
                    BuyPlayerRequest buyPlayerRequest = (BuyPlayerRequest) o;
                    main.playerJustBought = buyPlayerRequest.getbPlayer();
                    System.out.println(buyPlayerRequest.getbPlayer().getName() + " has been bought to main!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
