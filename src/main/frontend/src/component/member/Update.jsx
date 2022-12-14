import React,{useState,useEffect} from 'react';
import {BrowserRouter,Routes,Route,Link,useParams} from 'react-router-dom';
import axios from 'axios';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

let bcontent = '';
export default function Update(props) {
    const params = useParams();
    const [board , setBoard] = useState([]);
    const getboard = () => {
        axios
            .get("/board/getboard" , {params: {bno:params.bno} } )
            .then((res) => {setBoard(res.data); console.log(res.data);})
            .catch((err) => {alert(err)})
    }
    useEffect(getboard , []);

    const uboard = () =>{
        let boardform = document.querySelector('.boardform');
        let formdata = new FormData(boardform);
        formdata.set("bcontent",bcontent);
        formdata.set("bno",params.bno);
        axios
            .put('/board/updateboard' , formdata , {headers: {'Content-Type': 'multipart/form-data'} } )
            .then((res) => {
                if(res.data==true){alert('게시물 작성 성공'); window.location.href="/BoardList";}
                else{alert('게시물 작성 실패')}
            })
            .catch((err) => console.log(err));
    }

    return(
        <div>
        <form className="boardform">
            제목 : <input type="text" name="btitle" value={board.btitle}/>     <br/>
            내용 : <CKEditor
                     editor={ ClassicEditor }
                     data= {board.bcontent}
                     onChange={ ( event, editor ) => {
                         bcontent = editor.getData();
                     } }
                 />  <br/>
            파일 : <input type="file" name="bfile"/>      <br/>
            <button type="button" onClick={uboard}>전송</button>
        </form>
        </div>
    )
}