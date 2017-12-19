import React from 'react';
import ReactDOM from 'react-dom';
import App from './App.jsx';
import Header from './Header.jsx';
import UsersTable from './UsersTable.jsx';
import Stats from './Stats.jsx';
import Sessions from './Sessions.jsx';
import Datafiles from './Datafiles.jsx';
import Tablespaces from './Tablespaces.jsx';

ReactDOM.render(<UsersTable />, document.getElementById('userstable'));
ReactDOM.render(<Stats />, document.getElementById('stats'));
ReactDOM.render(<Datafiles />, document.getElementById('datafiles'));
ReactDOM.render(<Tablespaces />, document.getElementById('tablespaces'));
ReactDOM.render(<Sessions />, document.getElementById('sessions'));
