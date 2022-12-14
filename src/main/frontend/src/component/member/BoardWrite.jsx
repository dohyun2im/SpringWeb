import React,{useState,useEffect} from 'react';
import axios from 'axios';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';


let bcontent = '';
export default function BoardWrite(props) {
    const [category , setCategory] = useState('');
    const [bcno , setBcno] = useState(0);
    const [categoryList , setCategoryList] = useState([]);


    const getbcategory = () => {
        axios
            .get("/board/getcategory")
            .then((res) => {setCategoryList(res.data);})
            .catch((err) => {alert(err)})
    }
    useEffect(getbcategory , []);


    const setbcategory = () =>{
        axios
            .post("/board/setcategory" , {category : category} )
            .then((res) => {
                    if(res.data == true){
                        alert('등록 성공');
                    }
                    else{
                        alert('등록 실패');
                    }
                }
            )
            .catch((err) =>console.log(err));
    };
    const setboard = () =>{
        let boardform = document.querySelector('.boardform');
        let formdata = new FormData(boardform);
        formdata.set("bcontent",bcontent);
        axios
            .post('/board/setboard' , formdata , {headers: {'Content-Type': 'multipart/form-data'} } )
            .then((res) => {
                if(res.data==true){alert('게시물 작성 성공'); window.location.href="/BoardList";}
                else{alert('게시물 작성 실패')}
            })
            .catch((err) => console.log(err));
    }
    return (
        <div>
          <h1>write</h1>
          카테고리작성<input type="text" value={category} onChange={ (e)=>{ setCategory(e.target.value); } } />
          <button type="button" onClick={setbcategory}>추가</button>

         <form className="boardform">
            <select name="cno">
                {categoryList.map((e)=>{
                                 return (<option value={e.cno} >{e.category}</option>)
                              })
                }
            </select>
            제목 : <input type="text" name="btitle"/>     <br/>
            내용 : <CKEditor
                     editor={ ClassicEditor }
                     data="<p>입력</p>"
                     onChange={ ( event, editor ) => {
                         bcontent = editor.getData();
                     } }
                 />  <br/>
            파일 : <input type="file" name="bfile"/>      <br/>
            <button type="button" onClick={setboard}>전송</button>
          </form>
        </div>
    );
}