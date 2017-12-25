import React from 'react';

class Stats extends React.Component {

    constructor(props) {
        super(props);

        this.state = {data : []};
        this.Stats = this.Stats.bind(this);
    }

    componentDidMount() {
        this.Stats();
        setInterval(this.Stats, 5000);
    }

    Stats() {
        $.getJSON("http://localhost:8083/stats", (json) => {
            this.setState({data: json});
        });
    }

    getValue(name) {

        let final;
        {this.state.data.map(function(item, key) {

            if (item.STAT_NAME == name) {
                final = item.VALUE;
            }
        })}

        return final;
        
    }

    render() {

        return (

            <div className="row">

                <div className="col-md-1 col-sm-1"></div>
                <div className="col-md-2 col-sm-6">
                    <div className="knob-container">
                        <input className="knob" data-width="200" data-min="0" data-max="25" data-displayprevious="true" value={this.getValue("USERS")} data-fgcolor="#262626" readOnly="true"/>
                        <h3 className="text-muted text-center">Users</h3>
                    </div>
                </div>
                <div className="col-md-2 col-sm-6">
                    <div className="knob-container">
                        <input className="knob" data-width="200" data-min="0" data-max="100" data-displayprevious="true"  data-fgcolor="#666666" readOnly="true"  value={this.getValue("SESSIONS")}/>
                        <h3 className="text-muted text-center">Sessions</h3>
                    </div>
                </div>
                <div className="col-md-2 col-sm-6">
                    <div className="knob-container">
                        <input className="knob" data-width="200" data-min="0" data-max="100" data-displayprevious="true" value={this.getValue("MEMORY") | 0} data-fgcolor="#262626" readOnly="true"/>
                        <h3 className="text-muted text-center">MEMORY(MB)</h3>
                    </div>
                </div>
                <div className="col-md-2 col-sm-6">
                    <div className="knob-container">
                        <input className="knob" data-width="200" data-min="0" data-max="12" data-displayprevious="true" value={this.getValue("NUM_CPUS")} data-fgcolor="#666666" readOnly="true"/>
                        <h3 className="text-muted text-center">CPUs</h3>
                    </div>
                </div>
                <div className="col-md-2 col-sm-6">
                    <div className="knob-container">
                        <input className="knob" data-width="200" data-min="0" data-max="1000" data-displayprevious="true" value={this.getValue("SQL")} data-fgcolor="#262626" readOnly="true"/>
                        <h3 className="text-muted text-center">#SQL</h3>
                    </div>
                </div>

                <div className="col-md-1 col-sm-1"></div>
                {this.state.data.map(function(item, key) {

                    return (
                        <div key={key}> </div>
                    );

                })}
            </div>

        );
    }
}


export default Stats;

