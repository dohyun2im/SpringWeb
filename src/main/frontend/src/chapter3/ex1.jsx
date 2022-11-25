const name = '소플';
const element = <h1>안녕 , {name} </h1>;
ReactDOM.render(element,document.getElementById('root'));


function formatName(user) {
    return user;
}

const user = { firstName: 'Inje', lastName: 'Lee'}
const element = (<h1>Hello , {formatName(user)} </h1>)
ReactDOM.render(element, document.getElementById('root'))



function getGreeting(user) {
    if(user){
        return <h1> Hello , {formatName(user)} </h1>;
    }
    else{
        return <h1> Hello , Stranger. </h1>;
    }
}

const element= <div> <h1>안녕하세요 !</h1> <h2>열심히 리액트를 공부해 봅시다!</h2> </div>