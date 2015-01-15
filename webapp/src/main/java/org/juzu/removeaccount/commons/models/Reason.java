package org.juzu.removeaccount.commons.models;

/**
 * Created by anhvt on 15/01/15.
 */
public enum Reason {
  REASON_1(1),
  REASON_2(2),
  REASON_3(3),
  REASON_4(4),
  REASON_5(5);

  private int id;
  private String content;

  Reason(int id){
    this.id = id;
  }
  public int getId(){
    return this.id;
  }
  public String getContent(){
    switch (this.id){
      case 1:
        return "I did not find the information, help, support, i was looking for";
      case 2:
        return "I'am using another main account exo eXo Community";
      case 3:
        return "i receive too much emails from the Community";
      case 4:
        return "After the evaluation, eXo platform is not the solution i am looking for";
      default:
        return "Other";
    }
  }
  public static Reason getReason(int id){
    for (Reason type: Reason.values()){
      if (type.getId() == id )
        return type;
    }
    return REASON_5;
  }
}
