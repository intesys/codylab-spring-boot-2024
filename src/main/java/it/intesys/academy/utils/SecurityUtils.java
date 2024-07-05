package it.intesys.academy.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityUtils {

  public static String getUsername() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User principal = (User) authentication.getPrincipal();
    String username = principal.getUsername();
    return username;
  }

}
