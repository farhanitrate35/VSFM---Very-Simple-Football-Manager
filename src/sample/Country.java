package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Country implements Serializable {
    private String name;
    private List<Player> players;

    public Country(String n) {
        name = n;
        players = new ArrayList<Player>();
    }

    public Country() {
        name = "";
        players = new ArrayList<Player>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayers(Player player) {
        players.add(player);
    }

    public int getNumberOfPlayers() {
        int num = players.size();
        return num;
    }

    public String imgCountry(){
        String name1 = "FLAG" + name;
        String result = name1 + ".png";
        return result;
    }
}
