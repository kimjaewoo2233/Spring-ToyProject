package com.example.demo.dto.security;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountPrincipal implements UserDetails {

       private String username;

       private String password;

       Collection<? extends GrantedAuthority> authorities;

       private String email;

       private String nickname;

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}

    public static AccountPrincipal of(String username,String password,String email,String nickname){
        Set<RoleType> roleTypeSet = Set.of(RoleType.USER);

        return new AccountPrincipal(
                username,
                password,
                roleTypeSet.stream()
                        .map(RoleType::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                email,
                nickname
        );
    }

    public static AccountPrincipal from(AccountDto dto){
        return AccountPrincipal.of(
                dto.getUserId(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getNickname()
        );
    }

    public AccountDto toDto(){
        return AccountDto.of(
                username,
                password,
                email,
                nickname
        );
    }


    public enum RoleType{
        USER("ROLE_USER"),
        ADMIN("ROLE_ADMIN");

        @Getter
        private final String name;

        RoleType(String name){
            this.name = name;
        }
    }
}

