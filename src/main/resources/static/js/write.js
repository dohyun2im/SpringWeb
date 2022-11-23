
function setboard(){
    let boardform = document.querySelector('.boardform');
    let data = new FormData(boardform);
    data.set("cno" , document.querySelector('.categorylist').value);
    $.ajax({
        url:"/board/setboard",
        type:"POST",
        data:data,
        contentType:false,
        processData:false,
        success:function (re) {
           location.href="/board/boardlist"
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
       let html = '<option name="cno" value="0">전체</option>';
       re.forEach(r=>{
         html+="<option name='cno' value='"+r.cno+"'>"+r.category+"</option>"
       })
       document.querySelector('.categorylist').innerHTML = html;
    }
})