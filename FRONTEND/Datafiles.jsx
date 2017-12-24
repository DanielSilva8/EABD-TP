import React from 'react';

class Datafiles extends React.Component {

    constructor(props) {
        super(props);

        this.state = {data : []};
        this.Datafiles = this.Datafiles.bind(this);
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

    formatBytes(bytes) {
        
    if(bytes < 1024) return bytes + " Bytes";
    else if(bytes < 1048576) return(bytes / 1024).toFixed(3) + " KB";
    else if(bytes < 1073741824) return(bytes / 1048576).toFixed(3) + " MB";
    else return(bytes / 1073741824).toFixed(3) + " GB";

    }

    render() {
        return (

            <table className="table table-bordered table-striped">
                <thead>
                <tr>
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
                            <td>{item.FILE_NAME}</td>
                            <td>{item.TABLESPACE_NAME}</td>
                            <td>{(item.BYTES / 1048576).toFixed(0)} MB</td>
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