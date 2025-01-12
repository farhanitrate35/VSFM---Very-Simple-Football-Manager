package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
    public static List<Player> PlayerList;
    static List<Player> PlayerList2;
    public static List<Player> MarketList;
    public static List<Club> ClubList;

    public static void makeMainDatabase() throws Exception {
        PlayerList = FileOperations.readFromFile();
    }

    public static void makeClubDatabase(String clubName) throws Exception {
        PlayerList2 = new ArrayList<>();
        for(Player p : PlayerList){
            if(p.getClub().equals(clubName)){
                PlayerList2.add(p);
            }
        }
    }

    public List<Player> getClubDatabaseFromServer(String clubName) {
        List<Player> players = new ArrayList<>();
        for(int i = 0; i < PlayerList.size(); i++){
            if(PlayerList.get(i).getClub().equals(clubName)){
                players.add(PlayerList.get(i));
            }
        }
        return players;
    }

    public static void makeMarketDatabase() throws Exception {
        MarketList = new ArrayList<>();
        /*for(int i = 0; i < PlayerList.size(); i++){
            if(PlayerList.get(i).getClub().equals("")){
                MarketList.add(PlayerList.get(i));
            }
        }*/
    }

    public static List<Player> updateClubDatabase(String clubName) throws Exception {
        List <Player> tempPlayerList2 = new ArrayList<>();
        for(Player p : PlayerList){
            if(p.getClub().equals(clubName)){
                tempPlayerList2.add(p);
            }
        }
        return tempPlayerList2;
    }

    public static void closeMainDatabase() throws Exception {
        FileOperations.writeToFile(PlayerList);
    }

    public void updateMainDatabase() throws Exception {
        FileOperations.writeToFile(PlayerList);
        PlayerList = FileOperations.readFromFile();
    }

    public List<Player> searchName(String N){
        List<Player> temp = new ArrayList<Player>();
        for(int i = 0; i < PlayerList2.size(); i++){
            if(PlayerList2.get(i).getName().equalsIgnoreCase(N)){
                temp.add(PlayerList2.get(i));
                break;
            }
        }
        return temp;
    }

    public List<Player> searchCountry(String C){
        List<Player> temp = new ArrayList<Player>();
        for(int i = 0; i < PlayerList2.size(); i++){
            if(PlayerList2.get(i).getCountry().equalsIgnoreCase(C)) {
                temp.add(PlayerList2.get(i));
            }
        }
        return temp;
    }

    public List<Player> searchClub(List<Player> L, String C){
        List<Player> temp = new ArrayList<Player>();
        for(Player p : L){
            if(p.getClub().equalsIgnoreCase(C)) {
                temp.add(p);
            }
        }
        return temp;
    }

    public List<Player> searchPosition(String C){
        List<Player> temp = new ArrayList();
        for(int i = 0; i < PlayerList2.size(); i++){
            if(PlayerList2.get(i).getPosition().equalsIgnoreCase(C)) {
                temp.add(PlayerList2.get(i));
            }
        }
        return temp;
    }

    public List<Player> searchSalary(double l, double u){
        List<Player> temp = new ArrayList();
        for(int i = 0; i < PlayerList2.size(); i++){
            if(PlayerList2.get(i).getSalary() >= l && PlayerList2.get(i).getSalary() <= u) {
                temp.add(PlayerList2.get(i));
            }
        }
        return temp;
    }

    public List<Player> searchMaxSalary(){
        List<Player> temp = new ArrayList();
        double a = 0;
        for(int i = 0; i < PlayerList2.size(); i++){
            if(a < PlayerList2.get(i).getSalary()){
                a = PlayerList2.get(i).getSalary();
            }
        }
        for(int i = 0; i < PlayerList2.size(); i++){
            if(a == PlayerList2.get(i).getSalary()){
                temp.add(PlayerList2.get(i));
            }
        }
        return temp;
    }

    public List<Player> searchMaxAge(){
        List<Player> temp = new ArrayList();
        int a = 0;
        for(int i = 0; i < PlayerList2.size(); i++){
            if(a < PlayerList2.get(i).getAge()){
                a = PlayerList2.get(i).getAge();
            }
        }
        for(int i = 0; i < PlayerList2.size(); i++){
            if(a == PlayerList2.get(i).getAge()){
                temp.add(PlayerList2.get(i));
            }
        }
        return temp;
    }

    public List<Player> searchMaxHeight(){
        List<Player> temp = new ArrayList();
        double a = 0;
        for(int i = 0; i < PlayerList2.size(); i++){
            if(a < PlayerList2.get(i).getHeight()){
                a = PlayerList2.get(i).getHeight();
            }
        }
        for(int i = 0; i < PlayerList2.size(); i++){
            if(a == PlayerList2.get(i).getHeight()){
                temp.add(PlayerList2.get(i));
            }
        }
        return temp;
    }

    public List<Country> listCountries() {
        List<Country> CountryList = new ArrayList<Country>();
        for(Player p : PlayerList){
            int i = 0;
            for(Country C : CountryList){
                if(p.getCountry().equals(C.getName())){
                    i = 1;
                    break;
                }
            }
            if(i == 0){
                CountryList.add(new Country(p.getCountry()));
            }
        }
        for(Player p : PlayerList){
            for(Country C : CountryList){
                if(p.getCountry().equals(C.getName())){
                    C.addPlayers(p);
                    break;
                }
            }
        }
        return CountryList;
    }

    public static List<Club> ListClubs() {
        List<Club> ClubList = new ArrayList<Club>();
        for (Player p : PlayerList) {
            int i = 0;
            for (Club C : ClubList) {
                if (p.getClub().equals(C.getName())) {
                    i = 1;
                    break;
                }
            }
            if (i == 0) {
                ClubList.add(new Club(p.getClub()));
            }
        }
        for (Player p : PlayerList) {
            for (Club C : ClubList) {
                if (p.getClub().equals(C.getName())) {
                    C.addPlayers(p);
                }
            }
        }
        return ClubList;
    }

    public String clubNameBuilder(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the name of the club:");
        String name = scan.nextLine();
        name = name.trim();
        return name;
    }

    public Club findClub(String name) {
        for(Club C : ClubList){
            if(name.equalsIgnoreCase(C.getName())){
                return C;
            }
        }
        return null;
    }

    public boolean printNullClub(Club cl){
        if(cl == null){
            System.out.println("No such club with this name");
            return true;
        }
        return false;
    }

    public boolean existPlayer(String n){
        n = n.trim();
        for(Player p : PlayerList){
            if(p.getName().equalsIgnoreCase(n)){
                return true;
            }
        }
        return false;
    }

    public Club returnClub(String n, List<Club> clubs){
        for(Club C : clubs){
            if(n.equalsIgnoreCase(C.getName())){
                return C;
            }
        }
        Club C = new Club(n);
        clubs.add(C);
        return C;
    }

    public void playerTransferred(Player p, String buyer) throws Exception {
        Club buyingClub = findClub(buyer);
        Club sellingClub = findClub(p.getClub());
        sellingClub.getPlayers().remove(p);
        p.setClub(buyingClub.getName());
        buyingClub.getPlayers().add(p);
        updateMainDatabase();
    }
}
