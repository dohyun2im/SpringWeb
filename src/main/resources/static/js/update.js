
let bno = sessionStorage.getItem("bno");

function updateboard(){
    let boardform = document.querySelector('.boardform');
    let data = new FormData(boardform);
    data.set('bno', bno);
    $.ajax({
        url:"/board/updateboard",
        type:"put",
        data:data,
        contentType:false,
        processData:false,
        success:function(re){
            location.href="/board/boardlist"
        }
    })
}