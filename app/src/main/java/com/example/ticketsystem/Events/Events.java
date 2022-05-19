package com.example.ticketsystem.Events;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Events")
public class Events {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String team1;

    private String team2;

    private String time_venue;

    @Override
    public String toString() {
      String s=team1;
      s+='\n';
      s+=team2;
      s+='\n';
      s+=time_venue;
      s+='\n';
      return s;
    }

    public Events(int id, String team1, String team2, String time_venue) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.time_venue = time_venue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTime_venue() {
        return time_venue;
    }

    public void setTime_venue(String time_venue) {
        this.time_venue = time_venue;
    }
}
