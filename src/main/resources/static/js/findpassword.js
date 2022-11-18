function findpassword(){
     let memail = document.querySelector('.memail').value

    $.ajax({
        type: "post",
        url: "/member/getPassword",
        data:{"memail":memail},
        success: function(re){
            alert("비밀번호 : " + re)
            location.href="/member/";
        }
    })
}