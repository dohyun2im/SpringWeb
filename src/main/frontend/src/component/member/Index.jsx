import React from 'react';
import Header from './Header';
import Footer from './Footer';
import Signup from './signup';
import Login from './Login';
import Board from './Board';
import Home from './Home';
import BoardWrite from './BoardWrite';
import Counter from '../../chapter7/ex1Hook';
import AttendanceBook from '../../chapter10/AttendanceBook';
import NameForm from '../../chapter11/NameForm';
import SignUp from '../../chapter11/SignUp';
import Chatting from '../chatting/Chatting';
import RoomWrite from '../chatting/RoomWrite';
import BoardList from './BoardList';
import Update from './Update';
import View from './View';
import Cal from '../../chapter12/Calculator';
import 'bootstrap/dist/css/bootstrap.css';
import {BrowserRouter,Routes,Route,Link} from 'react-router-dom';

export default function Index(props){
    return (
    <div className='container text-center'>
        <BrowserRouter>
            <Header/>
                <h3>메인</h3>
            <Routes>
                <Route path="/RoomWrite" element={<RoomWrite/>}/>
                 <Route path="/Cal" element={<Cal/>}/>
                <Route path="/member/Home" element={<Home/>}/>
                <Route path="/member/signup" element={<Signup/>} />
                <Route path="/member/Login" element={<Login/>} />
                <Route path="/member/Board" element={<BoardWrite/>} />
                <Route path="/AttendanceBook" element={<AttendanceBook/>} />
                <Route path="/NameForm" element={<NameForm/>} />
                <Route path="/SignUp" element={<SignUp/>} />
                <Route path="/BoardList" element={<BoardList/>} />
                <Route path="/view/:bno" element={<View/>} />
                <Route path="/Update/:bno" element={<Update/>} />
                <Route path="/Chatting" element={<Chatting/>} />
            </Routes>
            <Footer/>
        </BrowserRouter>
    </div>
    );
}