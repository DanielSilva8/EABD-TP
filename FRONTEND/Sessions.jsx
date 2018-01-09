import React from 'react';

class Sessions extends React.Component {
    constructor(props) {
        super(props);

        this.state = {data : []};
        this.Sessions = this.Sessions.bind(this);

    }

    componentDidMount() {
        this.Sessions();
       setInterval(this.Sessions, 5000);
    }

    Sessions() {
        $.getJSON("http://localhost:8083/sessions", (json) => {
            this.setState({data: json});
        });
    }

    render() {
        return (

            <table className="table table-bordered table-striped">
                <thead>
                <tr>
                    <th></th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Machine</th>
                    <th>Program</th>
                    <th>Status</th>
                </tr>
                </thead>

                <tbody>
                {this.state.data.map(function(item, key) {

                    return (
                        <tr key = {key}>
                            <td>{item.SID}</td>
                            <td>{item.SCHEMA}</td>
                            <td>{item.TYPE}</td>
                            <td>{item.MACHINE}</td>
                            <td>{item.PROGRAM}</td>
                            <td>{item.STATUS}</td>
                        </tr>
                    );

                })}
                </tbody>
            </table>
        );
    }
}
export default Sessions;

