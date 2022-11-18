function setdelete(){
    let mpassword = document.querySelector('.mpassword').value;

    $.ajax({
        type: "post",
        url: "/member/delete",
        data: {"mpassword":mpassword},
        success: function(re){
            alert(re)
        }
    })

}