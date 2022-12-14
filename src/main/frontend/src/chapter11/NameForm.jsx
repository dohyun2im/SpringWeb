import React,{useState} from 'react';

export default function Nameform(props) {
    const [value,setValue] = useState('');
    const [value2,setValue2] = useState('');
    const [value3,setValue3] = useState('포도');

    const handleChange = (event) => {setValue(event.target.value);}
    const handleChange2 = (event) => {setValue2(event.target.value);}
    const handleChange3 = (event) => {setValue3(event.target.value);}

    const handleSubmit = (event) => {
        event.preventDefault();
        alert(value +" "+ value2 +" "+ value3);
    }

    return (
        <form onSubmit={handleSubmit}>
            <label>
                 Name <input type="text" value={value} onChange={handleChange} />
            </label>

            <label>
                 Req <textarea value={value2} onChange={handleChange2} />
            </label>

             <label>
                 select
                 <select value={value3} onChange={handleChange3}>
                    <option>사과</option>
                    <option>바나나</option>
                    <option>포도</option>
                    <option>수박</option>
                 </select>
            </label>

            <button type='submit'>제출</button>
        </form>
    )
}
