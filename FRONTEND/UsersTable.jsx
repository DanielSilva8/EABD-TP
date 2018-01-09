import React from 'react';

class UsersTable extends React.Component {

    constructor(props) {
        super(props);

        this.state = {user : []};
        this.UsersTable = this.UsersTable.bind(this);
    }


    componentDidMount() {
    
      this.UsersTable();
      setInterval(this.UsersTable, 5000);
    }

    UsersTable() {
        
        $.getJSON("http://localhost:8083/users", (json) => {
            this.setState({user: json});
        });
    }

    render() {

        var usernr = 0;
        return (
            <table className="table table-bordered table-striped">
                <thead>
                <tr>
                    <th></th>
                    <th>Name</th>
                    <th>Created</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                 {this.state.user.map(function(item, key) {

                     return (
                         <tr key = {key}>
                             <td>{item.USER_ID}</td>
                            <td>{item.USERNAME}</td>
                            <td>{item.CREATED}</td>
                            <td>{item.STATUS}</td>
                        </tr>
                    );

                     usernr++;
                })}
                </tbody>
            </table>

        );
    }
}
export default UsersTable;