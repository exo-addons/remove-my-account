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

package org.juzu.removeaccount.portlet.frontend;

import juzu.Path;
import juzu.Response;
import juzu.View;
import juzu.request.SecurityContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.juzu.removeaccount.commons.models.Reason;
import org.juzu.removeaccount.commons.services.RemoveAccountService;

import javax.inject.Inject;

public class JuZFrontendApplication {

  @Inject
  RemoveAccountService removeAccountService;

  @Inject
  @Path("index.gtmpl")
  org.juzu.removeaccount.portlet.frontend.templates.index indexTpl;

  @View
  public Response.Content index(SecurityContext securityContext){
    String currentUserName = securityContext.getUserPrincipal().getName();
    JSONObject info = removeAccountService.getInfoUser(currentUserName);
    if (null != info){
      try {
        return indexTpl.with().set("email",info.get("email")).set("reasons", Reason.values()).ok();
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return Response.ok("something went wrong, cannot get user info");
  }
}
