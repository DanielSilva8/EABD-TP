import React from 'react';

class Stats extends React.Component {
    render() {

        return (
            <div className="row">
                <div className="col-md-3 col-sm-6">
                    <div className="knob-container">
                        <input className="knob" data-width="200" data-min="0" data-max="3000" data-displayprevious="true" value="2500" data-fgcolor="#262626" readOnly="true"/>
                        <h3 className="text-muted text-center">Users</h3>
                    </div>
                </div>
                <div className="col-md-3 col-sm-6">
                    <div className="knob-container">
                        <input className="knob" data-width="200" data-min="0" data-max="4500" data-displayprevious="true" value="3299" data-fgcolor="#666666" readOnly="true"/>
                        <h3 className="text-muted text-center">CPU Cores</h3>
                    </div>
                </div>
                <div className="col-md-3 col-sm-6">
                    <div className="knob-container">
                        <input className="knob" data-width="200" data-min="0" data-max="2700" data-displayprevious="true" value="1840" data-fgcolor="#262626" readOnly="true"/>
                        <h3 className="text-muted text-center">Sessions</h3>
                    </div>
                </div>
                <div className="col-md-3 col-sm-6">
                    <div className="knob-container">
                        <input className="knob" data-width="200" data-min="0" data-max="15000" data-displayprevious="true" value="10067" data-fgcolor="#666666" readOnly="true"/>
                        <h3 className="text-muted text-center">Memory</h3>
                    </div>
                </div>
            </div>

        );
    }
}
export default Stats;


