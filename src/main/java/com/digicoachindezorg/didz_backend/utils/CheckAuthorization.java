package com.digicoachindezorg.didz_backend.utils;

import com.digicoachindezorg.didz_backend.models.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CheckAuthorization {

    public static boolean isAuthorized(User user, Collection<GrantedAuthority> grantedAuthorityCollection, String loggedInUserEmail) {

        //admin is allowed to do everything
        for (GrantedAuthority a : grantedAuthorityCollection) {
            if (a.getAuthority().equals("ROLE_ADMIN")) {
                return true;
            }
        }

        return user.getPrivateEMail().equalsIgnoreCase(loggedInUserEmail);
    }
}
