import React,{useState} from 'react';

export default function SignUp(props){
    const [name,setName] = useState('');
    const [gender,setGender] = useState('남자');

    const handleChangeName = (event) => {
        setName(event.target.value);
    }
    const handleChangeGender = (event) => {
        setGender(event.target.value);
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        alert(gender);
        alert(name);
    }
    return(
        <form onSubmit={handleSubmit}>
            <label>
                이름 : <input type="text" value={name} onChange={handleChangeName} / >
            </label>
            <label>
                성별 :
                <select value={gender} onChange={handleChangeGender}>
                    <option value="남자">남자</option>
                    <option value="여자">여자</option>
                </select>
            </label>
            <button type="submit">전송</button>
        </form>
    );
}