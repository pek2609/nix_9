package ua.com.alevel.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.concurrent.atomic.AtomicBoolean;

public final class SecurityUtil {

    private SecurityUtil() {
    }

    public static boolean hasRole(String role) {
        Authentication authentication = SecurityUtil.getAuthentication();
        return authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equalsIgnoreCase(role));
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getUsername() {
        Authentication authentication = SecurityUtil.getAuthentication();
        User principal = (User) authentication.getPrincipal();
        return principal.getUsername();
    }
}
