import React,{useState,useEffect,useRef} from 'react';
export default function Chatting(props) {
    const [socketConnect,setSocketConnect] = useState(false);
    const [items,setItems] = useState([]);
    let ws = useRef(null);

    useEffect(()=>{
        if(!ws.current){
            ws.current=new WebSocket('ws://localhost:8080/Gochat');
            ws.current.onopen=()=>{setSocketConnect(true);}
            ws.current.onclose=(e)=>{ console.log(e);}
            ws.current.onerror=(e)=>{console.log(e);}
            ws.current.onmessage=(e)=>{
              let data = JSON.parse(e.data);
              setItems( (prevItems)=>[...prevItems,data]);
            }
        }
    } , [])

    const onMsg = () => {
        let msg = document.querySelector('.msg').value;
        if(socketConnect){
            ws.current.send(JSON.stringify( {message:msg} ));
        }
        document.querySelector('.msg').value='';
    }

    return(
        <>
        <div>
           접속상태:<span></span>
           채팅입력:<input type="text" className="msg" />
           <button type="button" onClick={onMsg} >전송</button>
        </div>
        <div>
            <h2>Chatting</h2>
            {
                items.map((item) => {
                    return <div>{JSON.stringify(item.message)}</div>;
                })
            }
        </div>
        </>
    )
}