/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.assign1;

import java.io.Serializable;
import java.util.Date;
//import javax.persistance.Entity;


/**
 *
 * @author sunny
 */
//@ENTITY
public class User implements Serializable{
    private String Uname;
//    @ID
//    @GeneratedValue(strategy = GenerationType.Identity)
    private String Email;
    private String Password;
    private int Coins;
    private int Studies;
    private int Participation;
    private Date Date;
    private String Token;
    
    public User() {
    }

    public User(String Uname, String Email, String Password, int Coins, int Studies, int Participation) {
        this.Uname = Uname;
        this.Email = Email;
        this.Password = Password;
        this.Coins = Coins;
        this.Studies = Studies;
        this.Participation = Participation;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String Uname) {
        this.Uname = Uname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getCoins() {
        return Coins;
    }

    public void setCoins(int Coins) {
        this.Coins = Coins;
    }

    public int getStudies() {
        return Studies;
    }

    public void setStudies(int Studies) {
        this.Studies = Studies;
    }

    public int getParticipation() {
        return Participation;
    }

    public void setParticipation(int Participation) {
        this.Participation = Participation;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    



    
    }
