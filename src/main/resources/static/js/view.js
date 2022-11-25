let bno = sessionStorage.getItem("bno");

$.ajax({
    url:"/board/getboard",
    type:"get",
    data:{"bno":bno},
    success:function(re){
        let html='<div>제목: '+re.btitle+'</div>'+
                 '<div>내용: '+re.bcontent+'</div>'+
                 '<div><a href="/board/filedownload/?filename='+re.filename+'">파일: '+re.filename.split("_")[1]+'</a></div>'+
                 '<div>조회수: '+re.bview+'</div>'
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