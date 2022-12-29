import React,{useState,useEffect} from 'react';
import {BrowserRouter,Routes,Route,Link,useParams} from 'react-router-dom';
import axios from 'axios';

export default function View(props) {
    const params = useParams();

    const [board , setBoard] = useState([]);
    const getboard = () => {
        axios
            .get("/board/getboard" , {params: {bno:params.bno} } )
            .then((res) => {setBoard(res.data); console.log(res.data);})
            .catch((err) => {alert(err)})
    }
    useEffect(getboard , []);

    const [Login , setLogin] = useState(null);
    const getlogin = () => {
        axios.get('/member/getloginMno').then( res => { setLogin(res.data.split('@')[0]); })
    }
    useEffect(getlogin , []);

    const onDelete = () => {
        axios.delete('/board/deleteboard' , {params:{bno:params.bno}} )
             .then( res => { console.log(res.data); window.location.href="/BoardList";})
    }
    const onUpdate = () => {
        window.location.href ="/update/"+params.bno;
    }

    return(
        <div>
            <div>{board.btitle}</div>
            <div dangerouslySetInnerHTML={{__html:board.bcontent}}></div>
            {board.bfilename != '' && <a href={"/board/filedownload?filename="+board.filename}>{board.filename}</a>}

            <div>
                {Login == board.memail &&  <button onClick={onUpdate} > 수정 </button>}
                {Login == board.memail &&  <button onClick={onDelete} >삭제</button>}
            </div>
        </div>
    )
}