
function setboard(){
    let board = {
        btitle: document.querySelector('.btitle').value ,
        bcontent: document.querySelector('.bcontent').value,
        bfile: document.querySelector('.bfile').value
    }
    $.ajax({
        url:"/board/setboard",
        type:"POST",
        data:JSON.stringify(board),
        contentType:"application/json",
        success:function (re) {
           location.href="/board/boardlist"
        }
    })
}