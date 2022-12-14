import React from 'react'
import Styles from './Notification.css'

class Notification extends React.Component {
    constructor(props) {
        super(props);
        this.state = {}
    }
    componentDidMount() {console.log(this.props.id+" 출생");}
    componentDidUpdate(){console.log(this.props.id+" 업뎃");}
    componentWillUnmount() {console.log(this.props.id+" 사망");}
    render() {
        return (
            <div className="wrapper">
                <span className="messageText">
                    {this.props.message}
                </span>
            </div>
        )
    }
}

export default Notification;