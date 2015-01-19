package org.exoplatform.removeaccount.filter;

import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.UserHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by anhvt on 14/01/15.
 */
public class RemoveAccountProfileFilter implements org.exoplatform.web.filter.Filter  {
  private final static Log log = ExoLogger.getLogger(RemoveAccountProfileFilter.class);
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    // check if you is already login
    HttpServletRequest req = (HttpServletRequest)request;
    HttpServletResponse res = (HttpServletResponse)response;
    String currentUser = req.getRemoteUser();
    if ( null != currentUser) {
      try{
        String reqURI = req.getRequestURI();
        String[] paths = reqURI.split("/");
        String usernameVisivited = paths[paths.length-1];
        if(!currentUser.equals(usernameVisivited)){
          UserHandler userHandler = CommonsUtils.getService(OrganizationService.class).getUserHandler();
          if (null == userHandler.findUserByName(usernameVisivited)){
            log.info(" webui remove filter cannot find user for "+usernameVisivited);
            res.sendRedirect("/portal/intranet/profile");
            return;
          }
        }
      }catch (Exception e){
        log.error("ERR remove account webui filter "+e.getMessage());
      }
    }
    chain.doFilter(request,response);
  }

}
