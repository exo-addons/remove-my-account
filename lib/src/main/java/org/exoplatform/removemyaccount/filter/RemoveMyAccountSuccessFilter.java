package org.exoplatform.removemyaccount.filter;

import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by exoplatform on 19/01/15.
 */
public class RemoveMyAccountSuccessFilter implements org.exoplatform.web.filter.Filter  {
  private final static Log log = ExoLogger.getLogger(RemoveMyAccountSuccessFilter.class);
  public static final String COOKIE_NAME_OAUTH = "rememberoauth";
  public static final String COOKIE_NAME_EXO = "rememberme";
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) servletRequest;
    HttpServletResponse res = (HttpServletResponse) servletResponse;
    HttpSession session = req.getSession();
    Cookie cookie = new Cookie(COOKIE_NAME_OAUTH, "");
    cookie.setPath(req.getContextPath());
    cookie.setMaxAge(0);
    res.addCookie(cookie);
    cookie = new Cookie(COOKIE_NAME_EXO, "");
    cookie.setPath(req.getContextPath());
    cookie.setMaxAge(0);
    res.addCookie(cookie);
    session.invalidate();
    res.sendRedirect("/portal");
    return;
  }

}
