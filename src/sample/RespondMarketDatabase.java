package sample;

import java.io.Serializable;
import java.util.List;

public class RespondMarketDatabase implements Serializable {
    private String msg;
    private List<Player> marketPlayers;

    public RespondMarketDatabase(String msg, List<Player> marketPlayers) {
        this.msg = msg;
        this.marketPlayers = marketPlayers;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Player> getMarketPlayers() {
        return marketPlayers;
    }

    public void setMarketPlayers(List<Player> marketPlayers) {
        this.marketPlayers = marketPlayers;
    }
}
