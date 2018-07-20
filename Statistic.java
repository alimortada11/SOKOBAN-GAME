/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_fx;

public class Statistic {
    private int level;
    private String time;

    public Statistic(int level, String time) {
        this.level = level;
        this.time = time;
    }

    public int getLevel() {
        return level;
    }

    public String getTime() {
        return time;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setTime(String time) {
        this.time = time;
    }
    

    
}
