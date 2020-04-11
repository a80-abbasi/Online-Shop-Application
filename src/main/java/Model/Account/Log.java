package Model.Account;

public abstract class Log {
    private String ID;
    private String date;

    public Log(String ID, String date) {
        this.ID = ID;
        this.date = date;
    }

    public Log() {
        this("", "");
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
