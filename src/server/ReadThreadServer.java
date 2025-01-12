package server;

import sample.*;
import util.NetworkUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadThreadServer implements Runnable{
    private Server server;
    private Thread thr;
    private NetworkUtil networkUtil;
    private LoginInformation currentClientInfo;

    public ReadThreadServer(NetworkUtil networkUtil, Server server) {
        this.server = server;
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }

    @Override
    public void run() {
        try{
            while(true) {
                Object o = networkUtil.read();
                if(o instanceof LoginInformation) {
                    currentClientInfo = (LoginInformation) o;
                    String s = server.loginChecker(currentClientInfo);
                    networkUtil.write(s);
                }
                else if(o instanceof String) {
                    String request = (String) o;
                    if (request.equals("makeClubDatabase")) {
                        List<Player> players = server.DB.getClubDatabaseFromServer(currentClientInfo.getUsername());
                        //List<Player> toBeRemoved = new ArrayList<>();
                        for(int i = 0; i < server.DB.MarketList.size(); i++) {
                            if(currentClientInfo.getUsername().equals(server.DB.MarketList.get(i).getClub())) {
                                //toBeRemoved.add(server.DB.MarketList.get(i));
                                //players.remove(server.DB.MarketList.get(i));
                                for(int j = 0; j < players.size(); j++) {
                                    if(server.DB.MarketList.get(i).getName().equals(players.get(j).getName())) {
                                        players.remove(j);
                                    }
                                }
                            }
                        }
                        RespondClubDatabase respondClubDatabase = new RespondClubDatabase("ClubDatabase", players);
                        networkUtil.write(respondClubDatabase);
                    } else if (request.equals("makeCountryList")) {
                        List<Country> countries = server.DB.listCountries();
                        RespondCountryCount respondCountryCount = new RespondCountryCount("CountryList", countries);
                        networkUtil.write(respondCountryCount);
                    } else if(request.equals("showTransferMarket")) {
                        //List<Player> tPlayers = server.DB.MarketList;
                        RespondMarketDatabase respondMarketDatabase = new RespondMarketDatabase("Market", server.DB.MarketList);
                        networkUtil.write(respondMarketDatabase);
                        System.out.println("Transfer Market Provided!!");
                    }
                }
                else if(o instanceof SellPlayerRequest) {
                    SellPlayerRequest sellPlayerRequest = (SellPlayerRequest) o;
                    server.DB.MarketList.add(sellPlayerRequest.getSoldPlayer());
                    System.out.println("Player added for transfer: " + sellPlayerRequest.getSoldPlayer().getName());
                    System.out.println("Players in the transfer market at present:");
                    for (Player p : server.DB.MarketList) {
                        System.out.println(p.getName());
                    }
                }
                else if(o instanceof BuyPlayerRequest) {
                    BuyPlayerRequest buyPlayerRequest = (BuyPlayerRequest) o;
                    //server.DB.MarketList.remove(buyPlayerRequest.getbPlayer());
                    //buyPlayerRequest.getbPlayer().setClub(buyPlayerRequest.getClubName());
                    for(int i = 0; i < server.DB.MarketList.size(); i++) {
                        if(buyPlayerRequest.getbPlayer().getName().equals(server.DB.MarketList.get(i).getName())) {
                            server.DB.MarketList.get(i).setClub(buyPlayerRequest.getClubName());
                            buyPlayerRequest.setbPlayer(server.DB.MarketList.get(i));
                            server.DB.MarketList.remove(i);
                            break;
                        }
                    }
                    networkUtil.write(buyPlayerRequest);
                    System.out.println(buyPlayerRequest.getbPlayer().getName() + " has been removed from the transfer market!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
