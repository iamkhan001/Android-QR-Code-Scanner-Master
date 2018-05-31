package studio.n.qrscanner;

/**
 * Created by Imran Khan on 31 may 2018.
 */

public class LogModel {

    private String id, date, time, text;


    public LogModel(String id, String date, String time, String name) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.text = name;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

}
