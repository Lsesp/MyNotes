package lsesp.mynotes;

import java.util.Date;

/**
 * Created by Luis on 12/8/2016.
 */

public class Task{

    private String description;
    private String dueDate;
    private String time;

    public Task(){

    }

    public Task(String description, String dueDate, String time){
        this.description = description;
        this.dueDate = dueDate;
        this.time = time;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setDueDate(String date){
        this.dueDate=date;
    }

    public String getDueDate(){
        return dueDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
