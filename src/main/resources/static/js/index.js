getMno()
function getMno(){
    $.ajax({
        url:"/member/getloginMno",
        type:"GET",
        success:function(re){
        alert(re)
            if(re!=""){
                html='';
                html+=    '<a href="/member/signup"><button type="button">회원가입</button></a>'+
                          '<a href="/member/login"><button type="button">로그인</button></a>'+
                          '<a href="/member/logout"><button type="button">로그아웃</button></a>'+
                          '<a href="/member/findpassword"><button type="button">비밀번호찾기</button></a>'+
                          '<a href="/member/updatepw"><button type="button">비밀번호수정</button></a>'+
                          '<a href="/member/setdelete"><button type="button">회원탈퇴</button></a>'+
                          '<a href="/board/boardlist"><button type="button">글 보기</button></a>'
                document.querySelector('.app').innerHTML= html;
            }
            else{
                html='';
                html+=    '<a href="/member/signup"><button type="button">회원가입</button></a>'+
                          '<a href="/member/login"><button type="button">로그인</button></a>'
                document.querySelector('.app').innerHTML= html;
            }
        }
    })
}
list()
function list(){
    $.ajax({
        url:"/member/list",
        type:"GET",
        success:function(re){
            let html='<tr><th>회원번호</th><th>아이디</th><th>비밀번호</th></tr>';
            re.forEach(l=>{
                html+="<tr><td>"+l.mno+"</td><td>"+l.memail+"</td><td>"+l.mpassword+"</td></tr>"
            })
            document.querySelector('.mtable').innerHTML=html;

        }
    })
}
