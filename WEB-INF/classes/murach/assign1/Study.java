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
public class Study implements Serializable{
    private String Sname;
    private String Scode;
    private Date DateCreated;
    private String Email;
    private String Question;
    private String ImageURL;
    private int ReqParticipants;
    private int ActParticipants;
    private String Description;
    private String Sstatus;
  private ArrayList<Answer> list;
//    private int choice;


    
    public Study(){}

    public Study(String Sname, String Scode, Date DateCreated, String Email, String Question, String ImageURL, int ReqParticipants, int ActParticipants, String Description, String Sstatus,ArrayList<Answer> List) {
        this.Sname = Sname;
        this.Scode = Scode;
        this.DateCreated = DateCreated;
        this.Email = Email;
        this.Question = Question;
        this.ImageURL = ImageURL;
        this.ReqParticipants = ReqParticipants;
        this.ActParticipants = ActParticipants;
        this.Description = Description;
        this.Sstatus = Sstatus;
        this.list = list;
               
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String Sname) {
        this.Sname = Sname;
    }

    public String getScode() {
        return Scode;
    }

    public void setScode(String Scode) {
        this.Scode = Scode;
    }

    public Date getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(Date DateCreated) {
        this.DateCreated = DateCreated;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String ImageURL) {
        this.ImageURL = ImageURL;
    }

    public int getReqParticipants() {
        return ReqParticipants;
    }

    public void setReqParticipants(int ReqParticipants) {
        this.ReqParticipants = ReqParticipants;
    }

    public int getActParticipants() {
        return ActParticipants;
    }

    public void setActParticipants(int ActParticipants) {
        this.ActParticipants = ActParticipants;
    }

    
    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getSstatus() {
        return Sstatus;
    }

    public void setSstatus(String Sstatus) {
        this.Sstatus = Sstatus;
    }

  public ArrayList<Answer> getList() {
        return list;
    }

    public void setList(ArrayList<Answer> list) {
        this.list = list;
    }

//    public int getChoice() {
//        return choice;
//    }
//
//    public void setChoice(int choice) {
//        this.choice = choice;
//    }
//       public double getAverage(){
//           double sum=0;
//           int choice;
//           for(int i= choice);
//      {
//            sum += choice.get(i);
//        }
//        return sum/choice.size();
//    }
//    
//    public double getMinimum(){
//        int min = choice.get(1);
//        for(int i : choice){
//            if (choice.get(i) < min){
//             min = choice.get(i);
//             }
//        }
//        return min;
//    }
//    
//    public double getMaximum(){
//        int max = choice.get(1);
//        for(int i : choice){
//            if (choice.get(i) > max){
//             max = choice.get(i);
//             }
//        }
//        return max;
//     }
}

 
