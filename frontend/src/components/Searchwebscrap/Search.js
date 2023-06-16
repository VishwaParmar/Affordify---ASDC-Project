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



    const [imageUrls, setImageUrls] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');

    const handleInputChange = (event) => {
        setSearchTerm(event.target.value);
    }



    const redirecttonewwindow=(props)=>{
        console.log("props"+props);
        window.open(props, "_blank");

    }


    const handleClick = async () => {

        var queryString = searchTerm;


        const credentialData = localStorage.getItem('token');
        var myHeaders1 = new Headers();
        myHeaders1.append("Authorization", "Bearer "+credentialData);

        var requestOptions1 = {
            method: 'GET',
            headers: myHeaders1,
            redirect: 'follow'
        };
        const datavalue = await fetch("http://csci5308vm24.research.cs.dal.ca:8085/scrap/productname/"+queryString,requestOptions1)

        const data = await datavalue.json();
        console.log("set");
        console.log(data);
        setImageUrls(data);
    };
    const renderCard = (card, index) => {

        return (
            <>


                <Card style={{ width: "18rem"  }} key={index} className="box">

                    <Card.Img  style ={{height:"350px"}} variant="top"  src={card.imageLink} />
                    <div>
                        <Card.Body>
                            <Card.Header>{card.productName}</Card.Header>
                            <Card.Text>{"ShopNames: "+card.store}</Card.Text>
                            <Card.Text> {"Price: "+card.productPrice}</Card.Text>
                        </Card.Body>
                    </div>
                    <CardActions>
                        <Button size="small" onClick={()=>redirecttonewwindow(`${card.productLink}`)}>
                            Buy
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
                <label id ="searchlabel">Search the Scrapped Products</label>
                <div id="searchInput">
                    <input type="text" value={searchTerm} onChange={handleInputChange} />

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