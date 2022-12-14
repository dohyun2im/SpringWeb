import React,{useState , useEffect} from 'react';

export default function Counter(props){
    const [count,setCount] = useState(0);
    useEffect(()=>{
        document.title = `총 ${count}번 클릭했습니다.`
        return ()=>{}
    });

    return(
        <div>
            <h1>Counter</h1>
            <p> 총 {count}번 입력했습니다.</p>
            <button onClick={ () => setCount(count+1) }>
                클릭
            </button>
        </div>
    );
}