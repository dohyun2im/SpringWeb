import React from 'react';
export default function board(props) {
    return(
        <div>
            <h1>list</h1>
            <a href="/board/write"><button className="btn  btn-secondary" type="button">글 쓰기</button></a>
            <select className="categorylist"></select>
            <table className="list">
            </table>
        </div>
    )
}