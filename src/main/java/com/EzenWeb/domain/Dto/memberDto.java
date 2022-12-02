package com.EzenWeb.domain.Dto;

import com.EzenWeb.domain.entity.MemberEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class memberDto implements UserDetails , OAuth2User{
    private int mno;
    private String memail;
    private String mpassword;
    private String mphone;
    private Set<GrantedAuthority> authoritySet;
    private Map<String, Object> Attributes;

    @Override
    public Map<String, Object> getAttributes() {
        return this.Attributes;
    }
    @Override
    public String getName() {
        return this.memail;
    }

    public MemberEntity toEntity(){
        return MemberEntity.builder()
               .mno(this.mno)
               .memail(this.memail)
               .mpassword(this.mpassword)
               .mphone(this.mphone)
               .build();
    }

    public void setAuthoritySet(Set<GrantedAuthority> authoritySet) {
        this.authoritySet = authoritySet;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authoritySet;
    }

    @Override
    public String getPassword() {
        return this.mpassword;
    }

    @Override
    public String getUsername() {
        return this.memail;
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
