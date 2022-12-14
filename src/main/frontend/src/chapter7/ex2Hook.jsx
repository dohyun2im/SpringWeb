import React,{useState , useEffect} from 'react';

export default function useUserStatus(props) {
    const [isOnline,setIsOnline] = useState(null);
    useEffect(()=>{
        function handleStateChange(State) {setIsOnline(state.isOnline);}
        ServerAPI.subscribeUserStatus(props.user.id, handleStateChange);
        return ()=>{serverAPI.unsubscribeUserStatus(props.user.id, handleStateChange)};
    });
    return isOnline;

}

function UserStatus(props) {
    const isOnline = useUserStatus(props.user.id);
    if(isOnline==null){return '대기중..'}
    return isOnline? '온라인':'오프라인'
}

function UserListItem(props){
    const isOnline = useUserStatus(props.user.id);
    return (
    <li style={{color:isOnline?'green':'black'}}>
        {props.user.name}
    </li>
    )
}