import React from 'react';
import ReactDOM from 'react-dom';
import UsersTable from './UsersTable.jsx';
import Stats from './Stats.jsx';
import Sessions from './Sessions.jsx';
import Datafiles from './Datafiles.jsx';
import Tablespaces from './Tablespaces.jsx';
import Chart from './Chart.jsx';


ReactDOM.render(<UsersTable />, document.getElementById('userstable'));
ReactDOM.render(<Stats />, document.getElementById('stats'));
ReactDOM.render(<Datafiles />, document.getElementById('datafiles'));
ReactDOM.render(<Tablespaces />, document.getElementById('tablespaces'));
ReactDOM.render(<Sessions />, document.getElementById('sessions'));
ReactDOM.render(<Chart />, document.getElementById('chart'));