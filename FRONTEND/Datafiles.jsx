import React from 'react';

class Datafiles extends React.Component {

    constructor(props) {
        super(props);

        this.state = {data : []};
    }


    componentDidMount() {
        this.Datafiles();
        setInterval(this.Datafiles, 5000);
    }

    Datafiles() {
        $.getJSON("http://localhost:8083/datafiles", (json) => {
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
                    <th>Tablespace</th>
                    <th>Size</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                {this.state.data.map(function(item, key) {

                    return (
                        <tr key = {key}>
                            <td>{item.DATAFILE_ID}</td>
                            <td>{item.NAME}</td>
                            <td>{item.TABLESPACE_NAME}</td>
                            <td>{item.DATAFILE_SIZE}</td>
                            <td>{item.STATUS}</td>
                        </tr>
                    );

                })}
                </tbody>
            </table>
        );
    }
}
export default Datafiles;