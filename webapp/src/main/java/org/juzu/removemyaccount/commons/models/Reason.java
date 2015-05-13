package org.juzu.removemyaccount.commons.models;

/**
 * Created by exoplatform on 15/01/15.
 */
public enum Reason {
  REASON_1(1),
  REASON_2(2),
  REASON_3(3),
  REASON_4(4);

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
        return "You did not find the information, help, support you are looking for";
      case 2:
        return "You are using another account";
      case 3:
        return "You receive too many emails";
      default:
        return "After evaluation, eXo Platform is not the solution you are looking for";
    }
  }
  public static Reason getReason(int id){
    for (Reason type: Reason.values()){
      if (type.getId() == id )
        return type;
    }
    return REASON_1;
  }
}
