let getcode = '';
let timer = 0;
let successcode ='';

function signup(){
    let info = {
        memail : document.querySelector('.memail').value,
        mpassword : document.querySelector('.mpassword').value,
        mphone : document.querySelector('.mphone').value
    }
    if(successcode==="인증성공"){
        $.ajax({
                type: "POST",
                url: "/member/setmember",
                data: JSON.stringify(info) ,
                contentType: "application/json",
                success: function(re){
                    location.href="/member/";
                }
            })
    }
    else{
        alert("인증 부탁드립니다.")
    }

}

function getauth(){
    let memail = document.querySelector('.memail').value
    $.ajax({
        type: "get",
        url: "/member/getauth",
        data: {"memail":memail},
        success: function(re){
            getcode = re;
            console.log(getcode)
            alert(" 발송 완료 ! ")
            document.querySelector('.getauth').innerHTML ="재인증번호 받기"
            timer = 120 ;
            settimer()
        }
    })
}
let timerinter = null;
function settimer(){
   timerinter = setInterval(function(){

        let minutes = parseInt(timer/60);
        let seconds = parseInt(timer%60);
        minutes = minutes < 10? "0"+minutes : minutes;
        seconds = seconds < 10? "0"+seconds : seconds;
        let timehtml = minutes+" : "+seconds
        document.querySelector('.timerbox').innerHTML = timehtml;
        timer--;
        if(timer < 0){
            clearInterval(timerinter);
        }
   },1000);

}

function authcode(){
    let authinput = document.querySelector('.authinput').value
    if(authinput == getcode){
        alert('일치')
        clearInterval(timerinter);
        getcode=null;
        timer=0;
        document.querySelector('.timerbox').innerHTML = "인증성공"
        successcode="인증성공"
    }else{
        alert('불일치')
    }
}

