package org.juzu.removemyaccount.commons.services;

import org.json.JSONObject;
import org.juzu.removemyaccount.commons.models.Account;

/**
 * Created by exoplatform on 08/01/15.
 */
public interface RemoveMyAccountService {
  public Account storeAccount(Account account);
  public JSONObject getInfoUser(String username);
}
