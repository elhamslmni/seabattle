package Model;

public class User {


    String username,pasword;
    int gamesnumber,wins;
    boolean online=false;

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public int getGamesnumber() {
        return gamesnumber;
    }

    public void setGamesnumber(int gamesnumber) {
        this.gamesnumber = gamesnumber;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }
}
