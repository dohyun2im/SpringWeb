import React from 'react';
import Header from './Header';
import Footer from './Footer';
import Signup from './signup';
import {BrowserRouter,Routes,Route,Link} from 'react-router-dom';
export default function Index(props){
    return (
    <div>
        <BrowserRouter>
            <Header/>
                <h3>메인</h3>
            <Footer/>
            <Routes>
                <Route path="/" />
                <Route path="/member/signup" element={<Signup/>} />
            </Routes>
        </BrowserRouter>
    </div>
    );
}