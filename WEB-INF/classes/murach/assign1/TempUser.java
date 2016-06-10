/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.assign1;


import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author sunny
 */
public class TempUser implements Serializable{
    
    private String Uname;
    private String Email;
    private String Password;
    private Date IssueDate;
    private String Token;
    
    public  TempUser(){}

    public TempUser(String Uname, String Email, String Password, Date IssueDate, String Token) {
        this.Uname = Uname;
        this.Email = Email;
        this.Password = Password;
        this.IssueDate = IssueDate;
        this.Token = Token;
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

    public Date getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(Date IssueDate) {
        this.IssueDate = IssueDate;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }
    
    
    
    
    
    
    
}
