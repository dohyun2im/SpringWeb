
function setvisit(){
    let visit = {
    cno : document.querySelector('.categorylist').value ,
    content :  document.querySelector('.visit').value
    }
    $.ajax({
        url:"/board/setvisit",
        type:"post",
        data:JSON.stringify(visit),
        contentType:"application/json",
        success:function(re){
            alert('성공')
            getvisit()
        }
    })
}
getvisit()
function getvisit(){
    let category = document.querySelector('.categorylist').value;
    if(category===''){
    category=0;
    }
    $.ajax({
        url:"/board/getvisit",
        type:"get",
        data:{"category":category},
        success:function(re){
            let html = '<tr><th>게시물번호</th><th>카테고리</th><th>내용</th></tr>';
            re.forEach(e=>{
                html+="<tr><td>"+e.vno+"</td><td>"+e.category+"</td><td>"+e.content+"</td></tr>";
            })
            document.querySelector('.visitlist').innerHTML = html;
        }
    })
}

function setcategory(){
    let category = document.querySelector('.categoryname').value;
    $.ajax({
        url:"/board/setcategory",
        type:"POST",
        data:{"category":category},
        success:function (re) {
           location.reload();
        }
    })
}

$.ajax({
    url:"/board/getcategory",
    type:"get",
    success:function (re) {
       let html = '<option value="0">선택</option>';
       re.forEach(r=>{
         html+="<option value='"+r.cno+"'>"+r.category+"</option>"
       })
       document.querySelector('.categorylist').innerHTML = html;
    }
})