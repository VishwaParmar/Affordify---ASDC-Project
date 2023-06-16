import React from "react";
import { Typography,AppBar,Card,CardActions,CardContent,CardMedia,CssBaseline,Grid,Toolbar, Container,Button,ButtonGroup, makeStyles } from "@material-ui/core";
import useStyles from "./style";
import './UserCss.css';
import SideBar from "../SideBar/SideBar";


function ResponsiveAppBar() {

    const classes=useStyles();
   
   

  return (
    <div>
    <SideBar/>
    <div className="contact">
<div className={classes.container}>
            <Container maxWidth="sm" style={{marginTop: '30px'}}>
            </Container>
        </div>
        <Grid container spacing={4} justifyContent="center">
            
            <Grid item key={Card} xs={12} sm={6} md={4}>
                <Card className={classes.card}>

                     <CardContent className={classes.cardContent}>
                                             <Typography gutterBottom varient="h5" style={{fontSize: '30px'}}>
                                             Contact Details
                                             <Typography>
                                             Affordify will make grocery shopping easier and more convenient than ever before! We understand that keeping track of grocery prices can be a time-consuming and daunting task. That's why we have developed a powerful web scraping tool that enables you to quickly and easily retrieve up-to-date pricing information from your favorite grocery websites. With our tool, you can save time and money by making informed purchasing decisions based on the latest pricing data available. Say goodbye to endless searching and comparing prices, and say hello to effortless grocery shopping with our innovative tool.

                                             </Typography>
                                             <Typography>
                                             For further queries regarding the application contact:
                                             </Typography>
                                             </Typography>
                                             <Typography>
                                             <Typography>
                                             Kovarthanan Murugan kv948064@dal.ca

                                             </Typography>

                                             <Typography>
                                             Vishwa Pankajbhai Parmar vs623903@dal.ca
                                             </Typography>
                                             <Typography>
                                             Giri Sharan Reddy Pusuluru gr861188@dal.ca
                                             </Typography>
                                             <Typography>
                                             Kishoreganesh Sundararajan ks5240406@dal.ca
                                             </Typography>

                                             </Typography>
                                             <Typography>
                                             Jay Jitendrakumar Mulani jy850028@dal.ca
                                             </Typography>

                                          </CardContent>
     
                </Card>
                
                
            </Grid>

        </Grid>
    </div>
    </div>
    
  );
}
export default ResponsiveAppBar;
