package sample;

import java.io.Serializable;
import java.util.List;

public class RespondClubDatabase implements Serializable {

    private String msg;
    private List<Player> clubPlayers;

    public RespondClubDatabase(String msg, List<Player> clubPlayers) {
        this.msg = msg;
        this.clubPlayers = clubPlayers;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Player> getClubPlayers() {
        return clubPlayers;
    }

    public void setClubPlayers(List<Player> clubPlayers) {
        this.clubPlayers = clubPlayers;
    }
}
