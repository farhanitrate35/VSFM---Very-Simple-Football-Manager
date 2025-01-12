package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Club implements Serializable {
    private String name;
    private List<Player> players;

    public Club(String name) {
        this.name = name;
        players = new ArrayList<Player>();
    }

    public Club() {
        this.name = "";
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

    public void addPlayers(Player p) {
        players.add(p);
    }

    public void printSortedPlayers(List<Integer> sortedPlayers){
        for(int i : sortedPlayers){
            players.get(i).printInfo();
        }
    }

    public List<Player> maxSalary(){
        List<Player> maxSalaryPlayers = new ArrayList();
        double a = 0;
        for(Player p : players){
            if(a < p.getSalary()){
                a = p.getSalary();
            }
        }
        for(Player p : players){
            if(a == p.getSalary()){
                maxSalaryPlayers.add(p);
            }
        }
        return maxSalaryPlayers;
    }

    public List maxAge(){
        List<Integer> maxAgePlayers = new ArrayList();
        int a = 0;
        for(Player p : players){
            if(a < p.getAge()){
                a = p.getAge();
            }
        }
        for(Player p : players){
            if(a == p.getAge()){
                maxAgePlayers.add(players.indexOf(p));
            }
        }
        return maxAgePlayers;
    }

    public List maxHeight(){
        List<Integer> maxHeightPlayers = new ArrayList();
        double a = 0;
        for(Player p : players){
            if(a < p.getHeight()){
                a = p.getHeight();
            }
        }
        for(Player p : players){
            if(a == p.getHeight()){
                maxHeightPlayers.add(players.indexOf(p));
            }
        }
        return maxHeightPlayers;
    }

    public double totalYSalary() {
        double total = 0;
        for(Player p : players){
            total += p.getSalary();
        }
        total = 365 * (total / 7);
        return total;
    }

    public boolean numberTaken(int n){
        for(Player p : players){
            if(n == p.getNumber()){
                return true;
            }
        }
        return false;
    }
}
