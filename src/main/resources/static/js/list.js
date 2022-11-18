getboards();
function getboards(){
    let html = '<tr><th>제목</th><th>내용</th><th>파일</th></tr>';
    $.ajax({
        url:"/board/getboards",
        type:"get",
        success:function (re) {
          re.forEach(e=>{
            html += '<tr onclick="getview('+e.bno+')"><td>'+e.btitle+'</td><td>'+e.bcontent+'</td><td>'+e.bfile+'</td></tr>'
          })
          document.querySelector('.list').innerHTML = html;
        }
    })
}

function getview(bno){

    sessionStorage.setItem("bno",bno)
    location.href="/board/view";
}