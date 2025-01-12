package sample;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private String country;
    private int age;
    private double height;
    private String club;
    private String position;
    private int number;
    private double salary;

    public Player(){

    }

    public Player(String name, String country, int age, double height, String club, String position, int number, double salary) {
        this.name = name;
        this.country = country;
        this.age = age;
        this.height = height;
        this.club = club;
        this.position = position;
        this.number = number;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String firstName(){
        int idxLastSpace = name.lastIndexOf(' ');
        if(idxLastSpace == -1)
            return "";
        String first = name.substring(0, idxLastSpace);
        return first;
    }

    public String lastName(){
        int idxLastSpace = name.lastIndexOf(' ');
        String last = name.substring(idxLastSpace+1);
        return last;
    }

    public String imgPlayer(){
        String name1 = name.replace(" ", "");
        String result = name1 + ".png";
        return result;
    }

    public String imgCountry(){
        String name1 = "FLAG" + country;
        String result = name1 + ".png";
        return result;
    }

    public String imgClub(){
        String name1 = club.replace(" ", "");
        String result = name1 + ".png";
        return result;
    }

    public void printInfo() {
        System.out.println(name + "," + country + "," + age + "," + height + "," + club + "," + position + "," + number + "," + salary);
    }
}
