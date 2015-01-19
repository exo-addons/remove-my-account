package org.juzu.removeaccount.portlet.profile;

import juzu.Path;
import juzu.Response;
import juzu.View;
import juzu.request.SecurityContext;
import org.exoplatform.social.webui.Utils;

import javax.inject.Inject;

/**
 * Created by exoplatform on 15/01/15.
 */
public class JuzProfileApplication {

  @Inject
  @Path("index.gtmpl")
  org.juzu.removeaccount.portlet.profile.templates.index indexTpl;
  @View
  public Response index(SecurityContext securityContext){
    if(Utils.getViewerRemoteId().equals(Utils.getOwnerRemoteId()))
      return indexTpl.ok();

    return Response.ok("");
  }
}
