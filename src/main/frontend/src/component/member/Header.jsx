import React from 'react';
import {BrowserRouter,Routes,Route,Link} from 'react-router-dom';
export default function Header(props){
    return (
    <div>
        <h3 class='header_name'>헤더</h3>
        <ul>
            <li><Link to="">home</Link></li>
            <li><Link to="/member/signup"> 회원가입 </Link></li>
        </ul>
    </div>
    );
}