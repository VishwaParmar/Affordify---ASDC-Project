import React, {Component,userState} from 'react';
import {Button} from "../Button"
import '../NavBar/NavBar.css';
import './Search.css';
import { useEffect, useState } from "react";
import { Card } from "react-bootstrap";
import "./Box.css";
import {CardActions} from "@material-ui/core";
import SideBar from "../SideBar/SideBar";

function ImageGallery() {
  const options = [
    { label: 'noCondition', value: '' },
    { label: 'LessThan', value: 'lesserthan' },
    { label: 'GreaterThan', value: 'greaterthan' },
    
  ];
  const [selectedOption, setSelectedOption] = useState(options[0].value);

  const handleOptionChange = (event) => {
    const { value } = event.target;
    const selected = options.find((option) => option.value === value);
    console.log("selected");
    console.log(selected.value);
    setSelectedOption(selected.value);
  };

  const [imageUrls, setImageUrls] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [filter, setFilterTerm] = useState('');

  const handleInputChange = (event) => {
    setSearchTerm(event.target.value);
  }

  const handleInputFilter = (event) => {
    setFilterTerm(event.target.value);
  }
  
  const redirecttonewwindow=(props)=>{
    console.log("props"+props);
    window.open(props, "_blank");

  }
  const getPriceChart=(props)=>{
    localStorage.setItem("chartItem",props);
    window.location = '/chart';
 
   }

  const addFavorite = async (props) => {
    


    var raw =JSON.stringify({

      "userMail":localStorage.getItem('email'),
      "productID":props
    });
      

   const credentialData = localStorage.getItem('token');
   var myHeaders1 = new Headers();
      myHeaders1.append("Authorization", "Bearer "+credentialData);
      myHeaders1.append("Content-Type", "application/json");

      
      var requestOptions1 = {
        method: 'Post',
        headers: myHeaders1,
        body: raw,
        redirect: 'follow'
      };
    const datavalue = await fetch("http://csci5308vm24.research.cs.dal.ca:8085/favoriteproduct/savefavouriteproduct",requestOptions1)


  };


  
  const handleClick = async () => {

    var queryString = '';
    if (selectedOption == ''){
      queryString = searchTerm;
      
    }
    else{
      queryString = searchTerm+"/"+selectedOption+"/"+filter;

    }
 

    
    
   const credentialData = localStorage.getItem('token');
   var myHeaders1 = new Headers();
      myHeaders1.append("Authorization", "Bearer "+credentialData);
      
      var requestOptions1 = {
        method: 'GET',
        headers: myHeaders1,
        redirect: 'follow'
      };
    const datavalue = await fetch("http://csci5308vm24.research.cs.dal.ca:8085/product/productname/"+queryString,requestOptions1)

    const data = await datavalue.json();
  console.log("set");
  console.log(data);
    setImageUrls(data);
  };
  const renderCard = (card, index) => {

    return (
        <>
      

      <Card style={{ width: "18rem"  }} key={index} className="box">
      
        <Card.Img  style ={{height:"350px"}} variant="top"  src={card.link} />
        <div>
        <Card.Body>
          <Card.Header>{card.realName}</Card.Header>
          <Card.Text>{"ShopNames: "+card.shopName}</Card.Text>
          <Card.Text> {"Price: "+card.price}</Card.Text>
        </Card.Body>
        </div>
        <CardActions>
        <Button size="small" onClick={()=>redirecttonewwindow(`${card.productLink}`)}>
        Buy        
        </Button>
        <Button size="small" onClick={()=>addFavorite(`${card.productID}`)}>
        Add Favorite           
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
      <div id ="SearchItems">
  <label id ="searchlabel">Search the product</label>
  <div id="searchInput">
      <input type="text" value={searchTerm} onChange={handleInputChange} />
      <label id="dropdownlabel" htmlFor="dropdown">Price Range</label>
      <select id="pricedropdown" value={selectedOption.value} onChange={handleOptionChange}>
        {options.map((option) => (
          <option key={option.value} value={option.value}>
            {option.label}
          </option>
        ))}
      </select>
      <input id="inputCondition" type="text" value={filter} onChange={handleInputFilter} />
      
      <button onClick={handleClick}>Search</button>
      <div className="grid" >
        {imageUrls.map(renderCard)}
      </div>
    </div>
    </div>
    </div>
  );
}

export default ImageGallery;
