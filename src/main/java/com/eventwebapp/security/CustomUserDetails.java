package com.eventwebapp.security;

import com.eventwebapp.entities.users.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

/**
 * Created by xavier on 11/11/15.
 */
public class CustomUserDetails extends User implements UserDetails {

    private List<String> userRoles;

    // TODO: 11/11/15 Might want to add a User constructor to the User class.
    public CustomUserDetails(String firstname, String lastname, String password,
                             String email, String phone, List<String> userRoles) {
        super(firstname, lastname, password, email, phone);
        this.userRoles = userRoles;
    }

    public CustomUserDetails(User user, List<String> userRoles) {
        super(user);
        this.userRoles = userRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

}
