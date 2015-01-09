package org.juzu.removeaccount.commons.models;

/**
 * Created by exoplatform on 08/01/15.
 */
public class Account {
  private String username;
  private String reason;
  private String socialNetwork;
  private Boolean unsubscibeMarketingEmail;

  public Account(String username){
    this.setUsername(username);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getSocialNetwork() {
    return socialNetwork;
  }

  public void setSocialNetwork(String socialNetwork) {
    this.socialNetwork = socialNetwork;
  }

  public Boolean getUnsubscibeMarketingEmail() {
    return unsubscibeMarketingEmail;
  }

  public void setUnsubscibeMarketingEmail(Boolean unsubscibeMarketingEmail) {
    this.unsubscibeMarketingEmail = unsubscibeMarketingEmail;
  }
  public boolean checkValid(){
    if(null == this.getUsername() || "".equals(this.getUsername())){
      return false;
    }else if (null == this.getReason() || "".equals(this.getReason()))
      return false;
    return true;
  }
}
