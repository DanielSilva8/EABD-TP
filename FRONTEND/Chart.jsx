import React from 'react';
//import CanvasJS from 'canvasjs';

class Chart extends React.Component {

    constructor(props) {
        super(props);

        this.state = {data : []};
        this.Chart = this.Chart.bind(this);
    }


    componentDidMount() {
      this.Chart();
     setInterval(this.Chart, 5000);
    }

    Chart() {
        $.getJSON("http://localhost:8083/memory", (json) => {
            this.setState({data: json});
        });


var current = [];
var max = [];
var i = 0;

{this.state.data.map(function(item, key) {
current[i] = {y: Number(item.CURRENT_SIZE), label: item.SID};
max[i] = {y: Number(item.MAXIMUMSIZE), label: item.SID};

i++;
      
})}
setTimeout(1000);
var chart = new CanvasJS.Chart("chartContainer", {
            animationEnabled: false,
            backgroundColor: "transparent",
  title:{
    text: "Memory usage by sessions"   
  },
  axisY:{
    title:"Size"
  },

  axisX:{
    title: "Session Id",
    interval: 1
  },
  toolTip: {
    shared: true,
    reversed: true
  },
         data: [
  {
    type: "column",
    name: "Maximum",
    color: "#666666",
    showInLegend: "true",
    yValueFormatString: "0.#0 MB",
    dataPoints: max
  }, 
  {
    type: "stackedColumn",
    name: "Current",
    color: "#262626",
    showInLegend: "true",
    yValueFormatString: "0.#0 MB",

    dataPoints: current
  }]  
            
        });
    chart.render();
    }

    render() {

        return (
            <div id="chartContainer" style={{height: 300 + "px", width: "auto"}}></div>
        );
    }
}
export default Chart;