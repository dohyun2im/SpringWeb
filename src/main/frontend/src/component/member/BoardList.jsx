import React,{useState,useEffect} from "react";
import axios from 'axios';
import Pagination from 'react-js-pagination';

export default function BoardList(props) {
    const [pageInfo , setPageInfo] = useState({cno:0 , page:1 , key: "" , keyword:"" });
    const [boardList, setBoardList] = useState( { list:[] } );
    const [category, setCategory] = useState( [] );
    function list(){
        axios
            .post("/board/getboards" , pageInfo )
            .then((res) => { setBoardList(res.data);  console.log(boardList);})
            .catch((err) => {console.log(err)})
    }
    useEffect( list , [pageInfo])


    function categorylist(){
        axios
            .get("/board/getcategory")
            .then((res) => {setCategory(res.data)})
            .catch((err) => {console.log(err)})
    }
    useEffect( categorylist , [])


    const Oncategory = (e) => {
        let number =e.target.value;
        alert(number)
        setPageInfo({cno:number , page:1 , key: "" , keyword:"" });
    }

    const Onpage = (page) => {
        setPageInfo({cno:pageInfo.cno , page:page , key: pageInfo.key , keyword:pageInfo.keyword });
    }
    const onSearch = () => {
        setPageInfo({
            cno:pageInfo.cno ,
            page:1 ,
            key: document.querySelector('.key').value ,
            keyword:document.querySelector('.keyword').value
        });
    }

    const loadView = (bno) => {
        window.location.href = '/view/'+bno;
    }
    return(
       <>
           <a href="/member/Board">글 쓰기</a>

           <h1>Board List</h1>

           <select className="cbox"  onChange={Oncategory}>
              {
                  category.map( (c)=>{
                     return(
                        <option value={c.cno}> {c.category} </option>
                     );
                  })
              }
           </select>

           <table className="table">
               {
                   boardList.list.map( (b)=>{
                        return(
                            <tr>
                                <th> {b.btitle} </th>
                                <td onClick={ ()=>loadView(b.bno) }> {b.bcontent} </td>
                                <td> {b.memail} </td>
                                <td> {b.bdate} </td>
                            </tr>
                        );
                    })
               }
           </table>

           <Pagination
                activePage={pageInfo.page}
                itemsCountPerPage={3}
                totalItemsCount={boardList.totalBoards}
                pageRangeDisplayed={5}
                onChange={Onpage}
           />

           <div className="searchBox">
              <select className="key">
                <option value="btitle">제목</option>
                <option value="bcontent">내용</option>
              </select>
              <input type="text" className="keyword" />
              <button type="button" onClick={onSearch}>검색</button>
           </div>
       </>
    )
}