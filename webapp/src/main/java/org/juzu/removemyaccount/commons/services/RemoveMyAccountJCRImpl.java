package org.juzu.removemyaccount.commons.services;

import org.exoplatform.services.jcr.ext.app.SessionProviderService;
import org.exoplatform.services.jcr.ext.common.SessionProvider;
import org.exoplatform.services.jcr.ext.hierarchy.NodeHierarchyCreator;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.User;
import org.json.JSONObject;
import org.juzu.removemyaccount.commons.models.Account;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jcr.*;

/**
 * Created by anhvt on 08/01/15.
 */
@Singleton
public class RemoveMyAccountJCRImpl implements RemoveMyAccountService {

  private static final Log log             = ExoLogger.getLogger(RemoveMyAccountJCRImpl.class);

  private static final String REMOVE_ACCOUNT_APP = "RemoveAccountApplication";

  private static final String  NODE_PROPS_USERNAME = "exo:username";

  private static final String  NODE_PROPS_REASON = "exo:reason";

  private static final String  NODE_PROPS_UNSUBSCRIBE_MKT_EMAIL = "exo:unsubscribe-marketing-email";

  @Inject
  SessionProviderService sessionProviderService;

  @Inject
  NodeHierarchyCreator nodeHierarchyCreator;

  @Inject
  OrganizationService organizationService;

  private Node getOrCreateRemoveAccountAppHome() throws Exception {
    Node removeAccApp = null;
    SessionProvider sProvider = sessionProviderService.getSystemSessionProvider(null);
    Node publicApp = nodeHierarchyCreator.getPublicApplicationNode(sProvider);
    try {
      if(publicApp.hasNode(REMOVE_ACCOUNT_APP))
        removeAccApp = publicApp.getNode(REMOVE_ACCOUNT_APP);
      else{
        removeAccApp = publicApp.addNode(REMOVE_ACCOUNT_APP, "nt:unstructured");
        publicApp.getSession().save();
      }
    } catch (Exception e) {
      log.error("ERR Remove Account App cannot get home folder");
    }
    return removeAccApp;
  }
  private void setNodeProperties(Node aNode,Account account) throws RepositoryException {
    aNode.setProperty(NODE_PROPS_USERNAME,account.getUsername());
    aNode.setProperty(NODE_PROPS_REASON,account.getReason());
    aNode.setProperty(NODE_PROPS_UNSUBSCRIBE_MKT_EMAIL,account.getUnsubscibeMarketingEmail());
  }
  private Account transferNode2Account(Node node) throws RepositoryException {
    if (null == node)
      return null;
    Account account = new Account("");
    PropertyIterator iter = node.getProperties("exo:*");
    Property p;
    String name;
    while (iter.hasNext()) {
      p = iter.nextProperty();
      name = p.getName();
      if (name.equals(NODE_PROPS_USERNAME)){
        account.setUsername(p.getString());
      }else if (name.equals(NODE_PROPS_REASON)){
        account.setReason(p.getString());
      }else if (name.equals(NODE_PROPS_UNSUBSCRIBE_MKT_EMAIL)){
        account.setUnsubscibeMarketingEmail(p.getBoolean());
      }
    }
    if (account.checkValid())
      return account;

    return null;
  }

  @Override
  public Account storeAccount(Account account) {

    Node homeApp = null;
    try {
      homeApp = this.getOrCreateRemoveAccountAppHome();
      Node accountNode = null;
      if (null != homeApp){
        if (homeApp.hasNode(account.getUsername())){
          accountNode = homeApp.getNode(account.getUsername());
        }else{
          accountNode = homeApp.addNode(account.getUsername());
          this.setNodeProperties(accountNode,account);
          homeApp.save();
        }
      }
      if (null != accountNode){
        if (null != organizationService.getUserHandler().removeUser(account.getUsername(),false))
          return this.transferNode2Account(accountNode);
        else
          log.error("ERR remove account => cannot remove user");
      }
    } catch (Exception e) {
      log.error("ERR remove account cannot store remove account info ");
      e.getMessage();
    }
    return null;
  }

  @Override
  public JSONObject getInfoUser(String username) {
    JSONObject info = null;
    try {
      User user = organizationService.getUserHandler().findUserByName(username);
      if (null != user){
        info = new JSONObject();
        info.put("username",username);
        info.put("email",user.getEmail());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return info;
  }
}
