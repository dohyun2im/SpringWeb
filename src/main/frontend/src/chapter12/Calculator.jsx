import React,{useState} from 'react';
import TemperatureInput from './TemperatureInput';

function BoilingVerdict (props){
    if(props.celsius>=100){
        return <p> 물이 끓습니다. </p>;
    }
    else{
        return <p> 물이 끓지 않습니다. </p>;
    }
}

function toCelsius(fahrenhiet){
    return ( (fahrenhiet-32) * 5/9);
}

function toFahrenhiet(celsius){
    return ( (celsius*9) / 5+32 );
}

function tryConvert (temperature,convert){
    const input = parseFloat(temperature);
    if(Number.isNaN(input)){ return "";}
    const output = convert(input);
    const rounded = Math.round(output*1000)/1000;
    return rounded.toString();
}

export default function Calculator(props) {
    const [temperature, setTemperature] = useState("");
    const [scale,setScale] = useState("c");
    const handleCelsiusChange=(e)=>{
        setTemperature(e); setScale("c");
    }
    const handleFahrenheitChange=(e)=>{
        setTemperature(e); setScale("f");
    }
    const fahrenhiet = scale === "c"? tryConvert(temperature,toCelsius) : temperature;
    const celsius = scale === "f"? tryConvert(temperature,toFahrenhiet) : temperature;
    return(
        <div>
            <TemperatureInput
                scale="c"
                temperature={celsius}
                onTemperatureChange={handleCelsiusChange}
            />
            <TemperatureInput
                scale="f"
                temperature={fahrenhiet}
                onTemperatureChange={handleFahrenheitChange}
            />
        </div>
    )
}