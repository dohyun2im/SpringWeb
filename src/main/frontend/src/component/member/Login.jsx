import React from 'react';
export default function Login(props){
    return(
        <div>
            <h1>로그인</h1>
            <form method="post" action="/member/loginprocess">
                이메일:<input type="email" name="memail" /><br/>
                패스워드<input type="password" name="mpassword" /><br/>
                <input type="submit" value="login" /><br/>
                <a href="/oauth2/authorization/kakao">카카오 로그인</a><br/>
                <a href="/oauth2/authorization/naver">네이버 로그인</a><br/>
                <a href="/oauth2/authorization/google">구글 로그인</a><br/>
            </form>
        </div>
    )
}