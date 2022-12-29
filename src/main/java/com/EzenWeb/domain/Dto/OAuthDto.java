package com.EzenWeb.domain.Dto;

import com.EzenWeb.domain.entity.MemberEntity;
import lombok.*;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OAuthDto {
    private String memail;
    private String mname;
    private String registrationId;
    private Map<String,Object> attributes;
    private String userInfo;

    public static OAuthDto of(String registrationId , String userInfo , Map<String,Object> Attributes){
        if (registrationId.equals("kakao")){
            return ofKakao(registrationId,userInfo,Attributes);
        }
        else if (registrationId.equals("naver")){
            return ofNaver(registrationId,userInfo,Attributes);
        }
        else if(registrationId.equals("google")){
            return ofGoogle(registrationId,userInfo,Attributes);
        }
        return null;
    }

    public static OAuthDto ofKakao(String registrationId , String userInfo , Map<String,Object> Attributes){
        Map<String,Object> kakao_account = (Map<String, Object>) Attributes.get(userInfo);
        Map<String,Object> profile = (Map<String, Object>) kakao_account.get("profile");
        return  OAuthDto.builder()
                        .memail((String)kakao_account.get("email"))
                        .mname((String)profile.get("nickname"))
                        .registrationId(registrationId)
                        .attributes(Attributes)
                        .userInfo(userInfo)
                        .build();
    }
    public static OAuthDto ofNaver(String registrationId , String userInfo , Map<String,Object> Attributes){
        System.out.println("naver"+Attributes);
        Map<String,Object> response = (Map<String, Object>) Attributes.get(userInfo);

        return OAuthDto.builder()
                       .memail((String) response.get("email"))
                       .mname((String) response.get("nickname"))
                       .registrationId(registrationId)
                       .userInfo(userInfo)
                       .attributes(Attributes)
                       .build();
    }
    public static OAuthDto ofGoogle(String registrationId , String userInfo , Map<String,Object> Attributes){
        return  OAuthDto.builder()
                        .memail((String)Attributes.get("email"))
                        .mname((String)Attributes.get("mname"))
                        .registrationId(registrationId)
                        .userInfo(userInfo)
                        .attributes(Attributes)
                        .build();
    }

    public MemberEntity toEntity(){
        return MemberEntity.builder()
                           .memail(this.memail)
                           .role("Role_Member")
                           .build();
    }
}
