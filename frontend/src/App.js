import './App.css';
import {BrowserRouter as Router,Routes,Route} from "react-router-dom";

import SignIn from './components/Signin'
import Page_signup from './components/Signup'
import NavBar from './components/NavBar/NavBar'
import SideBarDirect from './sidebardirect';
import Search from './components/Search/Search'
import UserDetails from './components/User/UserDetails';
import Chart from './components/Chart/Chart'

function App() {
  return (
    <div className="App">
      <SideBarDirect/>
  <Routes>
  <Route path='/' element={<SignIn />} />
  <Route path='/Signup' element={<Page_signup />} />
  <Route path='/userPage' element={<UserDetails/>} />

  </Routes>
      
    </div>
  );
}
export default App;
