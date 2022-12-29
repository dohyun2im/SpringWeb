import React,{useState} from 'react';
import Logo from '../../img/logo.png';
import {BrowserRouter,Routes,Route,Link} from 'react-router-dom';
import axios from 'axios';

export default function Header(props){

    const [Login , setLogin] = useState(null);
    axios.get('/member/getloginMno').then( res => { setLogin(res.data); })

    return (
    <div className='container text-center'>
        <div className='row'>
            <h3  className='col'><img class='logo' src={Logo} /></h3>
            <ul className='row'>
                {
                    Login==""?
                    (
                     <>

                        <li className='col'><a href="/member/home">home</a></li>
                        <li className='col'><a href="/member/signup"> 회원가입 </a></li>
                        <li className='col'><a href="/member/Login"> 로그인 </a></li>
                     </>
                    )
                    :
                    (
                     <>

                        <li>{Login}</li>
                        <li className='col'><a href="/member/home">hom</a></li>
                        <li className='col'><a href="/kimdohyun"> 로그아웃</a></li>
                        <li className='col'><a href="/member/Board"> 게시판쓰기</a></li>
                        <li className='col'><a href="/BoardList"> 게시판읽기 </a></li>
                     </>
                    )
                }
                <li className='col'><a href="/Chatting"> 채팅 </a></li>
            </ul>
        </div>
    </div>
    );
}