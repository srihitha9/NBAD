    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.assign1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author sunny
 */
public class Answer implements Serializable {
    private String Email;
    private int Choice;
    private String Scode; 
    private Date DateSubmitted;
//    private ArrayList<Answer> list;
    
    

    public Answer() {
    }

    public Answer(String Email, int Choice, Date DateSubmitted, String Scode) {
        this.Email = Email;
        this.Choice = Choice;
        this.Scode = Scode;
        this.DateSubmitted = DateSubmitted;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getChoice() {
        return Choice;
    }

    public void setChoice(int Choice) {
        this.Choice = Choice;
    }

    public String getScode() {
        return Scode;
    }

    public void setScode(String Scode) {
        this.Scode = Scode;
    }

    public Date getDateSubmitted() {
        return DateSubmitted;
    }

    public void setDateSubmitted(Date DateSubmitted) {
        this.DateSubmitted = DateSubmitted;
    }

//    public ArrayList<Answer> getList() {
//        return list;
//    }
//
//    public void setList(ArrayList<Answer> list) {
//        this.list = list;
//    }
//    
}
