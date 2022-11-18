function updatepassword(){
    let updatepassword = document.querySelector('.updatepassword').value;
    $.ajax({
        type: "put",
        url: "/member/updatepassword",
        data: {"updatepassword":updatepassword},
        success: function(re){
            alert("바뀜"+re)
            location.href="/member/";
        }
    })

}