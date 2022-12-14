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

                        <li className='col'><Link to="/member/home">home</Link></li>
                        <li className='col'><Link to="/member/signup"> 회원가입 </Link></li>
                        <li className='col'><Link to="/member/Login"> 로그인 </Link></li>
                     </>
                    )
                    :
                    (
                     <>

                        <li>{Login}</li>
                        <li className='col'><Link to="/member/home">home</Link></li>
                        <li className='col'><a href="/kimdohyun"> 로그아웃</a></li>
                        <li className='col'><Link to="/member/Board"> 게시판 </Link></li>
                     </>
                    )
                }

            </ul>
            <a href="/Logout"> 로그아웃</a>
        </div>
    </div>
    );
}