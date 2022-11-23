let bno = sessionStorage.getItem("bno");

$.ajax({
    url:"/board/getboard",
    type:"get",
    data:{"bno":bno},
    success:function(re){
        let html='<tr><th>제목: '+re.btitle+'</th><th>내용: '+re.bcontent+'</th><th>파일: '+re.filename+'</th><th>조회수: '+re.bview+'</th></tr>'
        document.querySelector('.view').innerHTML =html
    }
})



function deleteboard(){
    $.ajax({
        url:"/board/deleteboard",
        type:"delete",
        data:{"bno":bno},
        success:function(re){
            location.href="/board/boardlist"
        }
    })
}