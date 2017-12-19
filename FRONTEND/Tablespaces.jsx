import React from 'react';

class Tablespaces extends React.Component {

    constructor(props) {
        super(props);

        this.state = {data : []};
    }


    componentDidMount() {
       this.Tablespaces();
        setInterval(this.Tablespaces, 5000);
    }

    Tablespaces() {
        $.getJSON("http://localhost:8083/tablespaces", (json) => {
            this.setState({data: json});
        });
    }

    render() {
        return (

            <table className="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                {this.state.data.map(function(item, key) {

                    return (
                        <tr key = {key}>
                            <td>{item.NAME}</td>
                            <td>{item.STATUS}</td>
                        </tr>
                    );

                })}
                </tbody>
            </table>
        );
    }
}
export default Tablespaces;