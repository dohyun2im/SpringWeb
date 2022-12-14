import React from 'react';
const students = [{name: 'John'} ,{name: 'Jan'} ,{name: 'Jin'} ,{name: 'Jam'} ];
export default function AttendanceBook(props) {


    return(
        <ul>
            {
                students.map((s) => {return <li>{s.name}</li>;})
            }
        </ul>
    );
}