
import React, {Component} from 'react';
import {Button} from "../Button"
import './NavBar.css';
import  { useState,useEffect } from 'react';



function NavBar(){
  const [clicked, setclicked] = useState(false);
  const [showDropdown, setshowDropdown] = useState(false);
  const [notifications,setnotification] = useState([]);
  const [priceState,setPriceState] = useState(false);


  const handleClick= ()=>{
  setclicked(!clicked);

}
  const handleDropdownClick =()=>{

    setshowDropdown(!showDropdown);
  }

  const getUserPage = () => {
    window.location.href = "/userPage";
  }

  useEffect(() => {
    async function fetchData() {
      const credentialData = localStorage.getItem('token');
      const userEmail = localStorage.getItem('email');
      var myHeaders1 = new Headers();
      myHeaders1.append("Authorization", "Bearer "+credentialData);

      var requestOptions1 = {
        method: 'GET',
        headers: myHeaders1,
        redirect: 'follow'
      };
      const datavalue = await fetch("http://csci5308vm24.research.cs.dal.ca:8085/favoriteproduct/getfavoriteproduct/notification/"+userEmail,requestOptions1)

      const data = await datavalue.json();
      console.log(data.length);
      setnotification(data);
      if (data.length > 0){
        setPriceState(true);
      }else{
        setPriceState(false);
      }
    }
    fetchData();
  }, [])

  
  return (
          
    <nav className='NavbarItems'>
    <h1 className='navbar-logo'>Affordify<i className='fab '></i></h1>
    <div className='menu-icon' onClick={handleClick}>

           <i className={clicked ? 'fas fa-times':'fas fa-bars'}></i>
    </div>
    <ul className='nav-menu'>
    <a href="/search" class= "nav-links">Home</a>
      <div className="dropdownnav">
        <button className="dropdown-togglenav" onClick={handleDropdownClick}>
          Notifications
        </button>
        <ul className="dropdown-menunav">
          <li className={priceState ? 'pricealertshow': 'pricealerthide'}>!!Price Changed!!</li>

          {showDropdown && (


              notifications.map((item,index)=>{
                return (
                    <li key ={index} className="list">
                      <a className='productlink' href={item.productLink} >
                        {item.realName}

                      </a>
                    </li>
                )

              })

          )}
        </ul>
      </div>
  <a href="/" class= "nav-links">SignOut</a>
  </ul>

    <Button onClick={getUserPage}><i class="fas fa-user"></i></Button> 
    </nav>
        )


}

export default NavBar