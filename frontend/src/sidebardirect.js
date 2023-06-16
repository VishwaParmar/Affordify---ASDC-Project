import './App.css';
import {BrowserRouter as Router,Routes,Route, HashRouter} from "react-router-dom";
import {  Navigate } from 'react-router-dom';


import SideBar from "./components/SideBar/SideBar";
import VegetablesPage from "./components/SideBar/Categories/Vegetables"
import Fruits from "./components/SideBar/Categories/Fruits"
import Meat from "./components/SideBar/Categories/Meat"
import Favorite from "./components/SideBar/Categories/Favorite";
import Search from './components/Search/Search'
import UserDetails from './components/User/UserDetails';
import Chart from './components/Chart/Chart'
import Contact from './components/Contact/Contact'
import WebScrapSearch from './components/Searchwebscrap/Search'

function sidebardirect() {
    return (
      <div className="">
        
  
   {localStorage.getItem("Status")== "authenticated" &&(
    <Routes>
    
    <Route path='/services/vegetables' element={<VegetablesPage/>} />
    <Route path='/services/fruits' element={<Fruits/>} />
    <Route path='/services/meat' element={<Meat/>} />
    <Route path='/favorite' element={<Favorite/>} />

    <Route path='/search' element={<Search/>} />

    <Route path ='/chart' element={<Chart/>}/>
    <Route  path ='/contact' element={<Contact/>}/>
        <Route  path ='/webscrapsearch' element={<WebScrapSearch/>}/>
    </Routes>


   )}
    

        
      </div>
    );
  }
  export default sidebardirect;