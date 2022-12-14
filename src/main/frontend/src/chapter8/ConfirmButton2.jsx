import React, {useState} from 'react';

export default function ConfirmButton2(props) {
     const [isConfirmed , setIsConfirmed] = useState(false);

     const handleConfirm = () => {
        setIsConfirmed((prevIsConfirmed) => !prevIsConfirmed);
     };

     const handleConfirm2 = () => {
             setIsConfirmed((prevIsConfirmed) => !prevIsConfirmed);
     };

     return (
        <div>
            <button onClick={handleConfirm} disabled ={isConfirmed}>
                {isConfirmed ? '확인됨' : '확인하기'}
            </button>

            <button onClick={handleConfirm2}>
                버튼
            </button>
            { isConfirmed  && <input type="text"/> }
        </div>
     )
}