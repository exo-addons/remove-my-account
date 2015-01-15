package org.exoplatform.removeaccount.filter;

import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

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
public class RemoveAccountFilter implements org.exoplatform.web.filter.Filter  {
  private final static Log log = ExoLogger.getLogger(RemoveAccountFilter.class);
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    log.info(" i am remove account filter from webui ");
    HttpServletRequest req = (HttpServletRequest)request;
    HttpServletResponse res = (HttpServletResponse)response;
    res.sendRedirect("/portal/intranet/profile");
    return;
//    chain.doFilter(request,response);
  }
}
