import './App.css';
import {Line} from 'react-chartjs-2';
import { Chart as ChartJS,LineElement,CategoryScale,LinearScale,PointElement, Legend, Tooltip} from 'chart.js';
import { useEffect, useState } from "react";
import SideBar from '../SideBar/SideBar';

ChartJS.register(
  LineElement,
  CategoryScale,
  LinearScale,
  PointElement,
  Legend,
  Tooltip
)

function App() {

    const [date, setdate] = useState([]);
    const [price, setprice] = useState([]);

    useEffect(() => {
        var findItemID =localStorage.getItem("chartItem");
        async function fetchData() {
          const credentialData = localStorage.getItem('token');
         var myHeaders1 = new Headers();
            myHeaders1.append("Authorization", "Bearer "+credentialData);
            
            var requestOptions1 = {
              method: 'GET',
              headers: myHeaders1,
              redirect: 'follow'
            };
            console.log("findItemID"+findItemID);
          const datavalue = await fetch("http://csci5308vm24.research.cs.dal.ca:8085/product/productname/pricechart/"+findItemID,requestOptions1)
      
          const data = await datavalue.json();
        console.log("set");
        console.log(data);
        setdate(data.date);
        setprice(data.price);
        }
        fetchData();
      }, []);


  const data={
    labels:date,
    datasets:[{
      label:'Sales of Week',
      data:price,
      backgroundColor:'orange',
      borderColor:'black',
      pointBorderColor:'orange',
      fill:true,
      tension:0.4
  }]
  }
  const option={
    Plugin:{
      legend:true
    },
    scales:{
      y:{
          // min:3,
          // max:6
      }
    }
  }
  return (
    <div>
      <SideBar/>
    <div className="App">
      <h1 className='chartheading'>Chart</h1>
      <div style={{width:'1500px',height:'700px'}} className ="chart">
        <Line data={data} option={option}>

        </Line>
      </div>
    </div>
    </div>
  );
}

export default App;
