
let bno = sessionStorage.getItem("bno");

function updateboard(){
    let board = {
            bno: bno,
            btitle: document.querySelector('.btitle').value ,
            bcontent: document.querySelector('.bcontent').value,
            bfile: document.querySelector('.bfile').value
        }
    $.ajax({
        url:"/board/updateboard",
        type:"put",
        data:JSON.stringify(board),
        contentType:"application/json",
        success:function(re){
            location.href="/board/boardlist"
        }
    })
}