/*
 * Copyright 2014 eXo Platform SAS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.juzu.removemyaccount.portlet.frontend;

import juzu.Path;
import juzu.Resource;
import juzu.Response;
import juzu.View;
import juzu.plugin.ajax.Ajax;
import juzu.request.SecurityContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.juzu.removemyaccount.commons.models.Account;
import org.juzu.removemyaccount.commons.models.Reason;
import org.juzu.removemyaccount.commons.services.RemoveMyAccountService;

import javax.inject.Inject;

public class JuZFrontendApplication {

  @Inject
  RemoveMyAccountService removeMyAccountService;

  @Inject
  @Path("index.gtmpl")
  org.juzu.removemyaccount.portlet.frontend.templates.index indexTpl;

  @View
  public Response.Content index(SecurityContext securityContext){
    String currentUserName = securityContext.getUserPrincipal().getName();
    JSONObject info = removeMyAccountService.getInfoUser(currentUserName);
    if (null != info){
      try {
        return indexTpl.with().set("email",info.get("email")).set("reasons", Reason.values()).set("username",currentUserName).ok();
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return Response.ok("something went wrong, cannot get user info");
  }
  @Ajax
  @Resource
  public Response doRemoveMyAccount(SecurityContext securityContext, String reason, String unSubscribeMktEmail){
    String reasonContent = "";
    try {
      reasonContent  = Reason.getReason(Integer.parseInt(reason)).getContent();
    } catch(NumberFormatException e) {
      reasonContent = reason;
    }
    String currentUserName = securityContext.getUserPrincipal().getName();
    Account account = new Account(currentUserName);
    account.setReason(reasonContent);
    if (unSubscribeMktEmail.equals("1"))
      account.setUnsubscibeMarketingEmail(true);
    if (account.checkValid()){
      if(null != removeMyAccountService.storeAccount(account));
        return Response.ok("http://");
    }
    return Response.ok("nok");
  }
}
