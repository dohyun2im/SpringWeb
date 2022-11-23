$.ajax({
    url:"/board/getcategory",
    type:"get",
    success:function (re) {
      let html = '<option value="0">전체</option>';
       re.forEach(r=>{
         html+="<option value='"+r.cno+"'>"+r.category+"</option>"
       })
       document.querySelector('.categorylist').innerHTML = html;
    }
})
getboards()
function getboards(){
    let cno = document.querySelector('.categorylist').value;
    if(cno===''){cno=0;}
    let html = '<tr><th>카테고리</th><th>제목</th><th>내용</th><th>파일</th><th>작성자</th></tr>';
    $.ajax({
        url:"/board/getboards",
        type:"get",
        data:{"cno":cno},
        success:function (re) {
          re.forEach(e=>{
            html += '<tr onclick="getview('+e.bno+')"><td>'+e.category+'</td><td>'+e.btitle+'</td><td>'+e.bcontent+'</td><td>'+e.filename+'</td><td>'+e.memail+'</td></tr>'
          })
          document.querySelector('.list').innerHTML = html;
        }
    })
}

function getview(bno){

    sessionStorage.setItem("bno",bno)
    location.href="/board/view";
}