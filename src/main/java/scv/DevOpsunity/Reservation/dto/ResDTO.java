package scv.DevOpsunity.Reservation.dto;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component("ResDTO")
public class ResDTO {
    private int resNum;
    private String resDate;
    private String resTime;
    private String resSeatNum;
    private String user_id;
    private String branch;
    private LocalDateTime regTime;


    // Getters and Setters


    public int getResNum() {
        return resNum;
    }

    public void setResNum(int resNum) {
        this.resNum = resNum;
    }

    public String getResDate() {
        return resDate;
    }

    public void setResDate(String resDate) {
        this.resDate = resDate;
    }

    public String getResTime() {
        return resTime;
    }

    public void setResTime(String resTime) {
        this.resTime = resTime;
    }

    public String getResSeatNum() {
        return resSeatNum;
    }

    public void setResSeatNum(String resSeatNum) {
        this.resSeatNum = resSeatNum;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }
}