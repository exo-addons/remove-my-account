package org.juzu.removeaccount.commons.services;

import org.exoplatform.services.jcr.ext.app.SessionProviderService;
import org.exoplatform.services.jcr.ext.common.SessionProvider;
import org.exoplatform.services.jcr.ext.hierarchy.NodeHierarchyCreator;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.organization.OrganizationService;
import org.juzu.removeaccount.commons.models.Account;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jcr.Node;

/**
 * Created by anhvt on 08/01/15.
 */
@Singleton
public class RemoveAccountJCRImpl implements RemoveAccountService {

  private static final Log log             = ExoLogger.getLogger(RemoveAccountJCRImpl.class);

  private static final String REMOVE_ACCOUNT_APP = "RemoveAccountApplication";

  private static final String CREATED_DATE = "exo:createdDate";

  private static final String  NODE_PROPS_USERNAME = "exo:username";

  private static final String  NODE_PROPS_REASON = "exo:reason";

  private static final String  NODE_PROPS_SOC_NETWORK = "exo:soccialnetwork";

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

  @Override
  public Account storeAccount(Account account) {
    return null;
  }

  @Override
  public String getInfoUser(String username) {
    try {
      return organizationService.getUserHandler().findUserByName(username).getEmail();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
