import React, {Component,userState} from 'react';
import {Button} from "../../Button"
import '../../NavBar/NavBar.css';
import { useEffect, useState } from "react";
import { Card } from "react-bootstrap";
import "./Box.css";
import {CardActions} from "@material-ui/core";
import SideBar from "../SideBar";
function ImageGallery() {
  const [imageUrls, setImageUrls] = useState([]);
   
  const searchTerm = "meat";

  const getPriceChart=(props)=>{
    localStorage.setItem("chartItem",props);
    window.location = '/chart';
  }

  const redirecttonewwindow=(props)=>{
    console.log("props"+props);
    window.open(props, "_blank");
  
  }

  async function removeFavorite(props){

    console.log("removed favorite");
  
    
    
    
        
        const userEmail = localStorage.getItem('email');
       const credentialData = localStorage.getItem('token');
       var myHeaders1 = new Headers();
          myHeaders1.append("Authorization", "Bearer "+credentialData);
          
          var requestOptions1 = {
            method: 'GET',
            headers: myHeaders1,
            redirect: 'follow'
          };
        const datavalue = await fetch("http://csci5308vm24.research.cs.dal.ca:8085/favoriteproduct/deletefavoriteproduct/"+userEmail+"/"+props,requestOptions1)
        fetchData();
      }
   
    async function fetchData() {
    
      const userEmail = localStorage.getItem('email');
      const credentialData = localStorage.getItem('token');
      var myHeaders1 = new Headers();
        myHeaders1.append("Authorization", "Bearer "+credentialData);
        
        var requestOptions1 = {
          method: 'GET',
          headers: myHeaders1,
          redirect: 'follow'
        };
      const datavalue = await fetch("http://csci5308vm24.research.cs.dal.ca:8085/favoriteproduct/getfavouriteproduct/"+userEmail,requestOptions1)
  
      const data = await datavalue.json();
    console.log("set");
    console.log(data);
      setImageUrls(data);
    }

useEffect(() => {

  fetchData();
  }, []);

  const renderCard = (card, index) => {
    
    return (
        
        <>
      <Card style={{ width: "18rem"  }} key={index} className="box">
        <Card.Img style ={{height:"350px"}} variant="top"  src={card.link} />
        <Card.Body>
          <Card.Header>{card.realName}</Card.Header>
          <Card.Text>{"ShopNames: "+card.shopName}</Card.Text>
          <Card.Text> {"Price: "+card.price}</Card.Text>
        </Card.Body>
        <CardActions>
        <Button id ="buy" varient="outlined"  onClick={()=>redirecttonewwindow(`${card.productLink}`)}>Buy</Button>
        <Button size="small" onClick={()=>removeFavorite(`${card.productID}`)}>
        removeFavorite       
         </Button>
        <Button size="small" onClick={()=>getPriceChart(`${card.productID}`)}>
        Price Chart         
        </Button>
        </CardActions>
      </Card>
      </>
    );
  };
  return (
    <div >
<SideBar/>
<div id ="product">
      <div className="grid">

        {imageUrls.map(renderCard)}
      </div>
      </div>
      </div>

  );
}

export default ImageGallery;