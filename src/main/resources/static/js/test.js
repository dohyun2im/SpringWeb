alert('Hello')
function getMapping1(){
    $.ajax({
        url: '/api/v1/get-api/name',
        type: 'GET',
        success: re =>{
           alert(re);
        }
    })
}
function getMapping2(){
    $.ajax({
        url: '/api/v1/get-api/variable1/2',
        type: 'GET',
        success: re =>{
           alert(re);
        }
    })
}
function getMapping3(){
    $.ajax({
        url: '/api/v1/get-api/variable2/3',
        type: 'GET',
        success: re =>{
           alert(re);
        }
    })
}
 function getMapping4(){
     $.ajax({
         url: '/api/v1/get-api/variable3?variable=4',
         type: 'GET',
         success: re =>{
            alert(re);
         }
     })
 }
function getMapping5(){
    $.ajax({
        url: '/api/v1/get-api/request?a=1&b=2&c=3',
        type: 'GET',
        success: re =>{
           alert(re);
        }
    })
}
function getMapping6(){
    $.ajax({
        url: '/api/v1/get-api/request2?a=b',
        type: 'GET',
        success: re =>{
           alert(re);
        }
    })
}
function getMapping7(){
    $.ajax({
        url: '/api/v1/get-api/request3?name=1&email=2&organization=3',
        type: 'GET',
        success: re =>{
           alert(re);
        }
    })
}
function postMapping8(){
    $.ajax({
        url: '/api/v1/post-api/domain',
        type: 'post',
        success: re =>{
           alert(re);
        }
    })
}

function postMapping9(){

    let member = {
        name:'member1',
        email:"qewwqe2@NAVER.COM",
        organization:"QWEWQE"
    }

    $.ajax({
        url: '/api/v1/post-api/member',
        type: 'post',
        data: JSON.stringify(member),
        contentType:"application/json",
        success: re =>{
           alert(re);
        }
    })
}

function postMapping10(){
     let member = {
            name:'dto1',
            email:"dto@NAVER.COM",
            organization:"dto"
     }
    $.ajax({
        url: '/api/v1/post-api/member2',
        type: 'POST',
        data: JSON.stringify(member),
        contentType:"application/json",
        success: re =>{
           alert(re);
        }
    })
}

function putMapping11(){
     let member = {
            name:'11',
            email:"dto@NAVER.COM",
            organization:"dto"
     }
    $.ajax({
        url: '/api/v1/put-api/member',
        type: 'put',
        data: JSON.stringify(member),
        contentType:"application/json",
        success: re =>{
           alert(re);
        }
    })
}
function putMapping12(){
     let member = {
            name:'12',
            email:"dto@NAVER.COM",
            organization:"dto"
     }
    $.ajax({
        url: '/api/v1/put-api/member1',
        type: 'put',
        data: JSON.stringify(member),
        contentType:"application/json",
        success: re =>{
           alert(re);
        }
    })
}
function putMapping13(){
     let member = {
            name:'13',
            email:"dto@NAVER.COM",
            organization:"dto"
     }
    $.ajax({
        url: '/api/v1/put-api/member2',
        type: 'put',
        data: JSON.stringify(member),
        contentType:"application/json",
        success: re =>{
           console.log(re.name);
           console.log(re.email);
           console.log(re.organization);
        }
    })
}

function deleteMapping14(){
    $.ajax({
        url: '/api/v1/delete-api/ㄹㅇㅋㅋ',
        type: 'delete',
        success: re =>{
            alert(re);
        }
    })
}
function deleteMapping15(){
    $.ajax({
        url: '/api/v1/delete-api/request1?variable=ㄹㅇㅋㅋ...',
        type: 'delete',
        success: re =>{
            alert(re);
        }
    })
}