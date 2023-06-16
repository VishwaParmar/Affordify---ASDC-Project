import React from 'react';
import { Typography,AppBar,Card,CardActions,CardContent,CardMedia,CssBaseline,Grid,Toolbar, Container,Button,ButtonGroup, makeStyles } from "@material-ui/core";
import { PhotoCamera,ShoppingCart } from "@material-ui/icons";
import useStyles from "./style";
import './UserCss.css';
import SideBar from "../SideBar/SideBar";



function ResponsiveAppBar() {

    const classes=useStyles();
   
   

  return (
    <div>
    <SideBar/>
<div className={classes.container}>
            <Container maxWidth="sm" style={{marginTop: '30px'}}>
                <Typography variant="h4" align="center" color="textprimary" gutterBottom>
                    User Profile
                </Typography>
                {/* <Typography variant="h5" align="center" color="textSecoundary" paragraph>
                    Lorem ipsum dolor sit amet consectetur, adipisicing elit. Quisquam sapiente blanditiis nisi corporis sed facilis rerum odit quasi soluta ullam doloremque pariatur iste voluptates, consequuntur cum maiores sint provident adipisci?
                </Typography> */}
            </Container>
        </div>
        <Grid container spacing={4} justifyContent="center">
            
            <Grid item key={Card} xs={12} sm={6} md={4}>
                <Card className={classes.card}>
                    <CardMedia 
                        className={classes.cardMedia}
                        image='https://cdn-icons-png.flaticon.com/512/149/149071.png'
                        title="Image title"/>
                     <CardContent className={classes.cardContent}>
                        <Typography gutterBottom varient="h5">
                            Username
                        </Typography>
                        <Typography gutterBottom varient="h6">
                            <div id ="userEmail">
                           {localStorage.getItem("email")}
                           </div>
                        </Typography>
                     </CardContent>
                    <CardActions>

                        <a varient="contained" color='primary' href="/" class= "signout-links">SignOut</a>

                    </CardActions>      
                </Card>
                
                
            </Grid>

        </Grid>
    </div>
    
  );
}
export default ResponsiveAppBar;

