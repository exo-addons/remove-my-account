package org.juzu.removeaccount.commons.services;

import org.juzu.removeaccount.commons.models.Account;

/**
 * Created by exoplatform on 08/01/15.
 */
public interface RemoveAccountService  {
  public Account storeAccount(Account account);
  public String getInfoUser(String username);
}
