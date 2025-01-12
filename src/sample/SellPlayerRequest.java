package sample;

import java.io.Serializable;

public class SellPlayerRequest implements Serializable {
    private Player soldPlayer;
    private String msg;

    public SellPlayerRequest(Player soldPlayer, String msg) {
        this.soldPlayer = soldPlayer;
        this.msg = msg;
    }

    public Player getSoldPlayer() {
        return soldPlayer;
    }

    public void setSoldPlayer(Player soldPlayer) {
        this.soldPlayer = soldPlayer;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
