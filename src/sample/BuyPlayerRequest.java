package sample;

import java.io.Serializable;

public class BuyPlayerRequest implements Serializable {
    private String msg;
    private Player bPlayer;
    private String clubName;

    public BuyPlayerRequest(String msg, Player bPlayer, String clubName) {
        this.msg = msg;
        this.bPlayer = bPlayer;
        this.clubName = clubName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Player getbPlayer() {
        return bPlayer;
    }

    public void setbPlayer(Player bPlayer) {
        this.bPlayer = bPlayer;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
}
