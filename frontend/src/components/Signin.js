import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import back from '../components/back5.jpg';


const theme = createTheme();

export default function SignIn() {
  localStorage.setItem('token','');
  localStorage.setItem('email','');
  localStorage.setItem('Status','');
  const myStyle={
    backgroundImage: `url(${back})`,
    height:'100vh',
    marginTop:'-70px',
    backgroundSize: '100% 100%',
    backgroundRepeat: 'no-repeat'
};
  
  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      email: data.get('email'),
      password: data.get('password'),
    });
    var accesstoken;
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Authorization", "Basic "+ btoa(data.get('email') +":"+data.get('password')));
    
    var requestOptions = {
      method: 'GET',
      headers: myHeaders,
      redirect: 'follow'
    };
    console.log("kova1");
    fetch("http://csci5308vm24.research.cs.dal.ca:8085/afford/login", requestOptions)
      .then(response => response.text())
      .then(result => gethomepage(result))
      .catch(error =>  window.alert("wrong credentials"));
  
  
    function gethomepage(result){
      var myHeaders = new Headers();
      localStorage.setItem('token',result);
      myHeaders.append("Authorization", "Bearer "+result);
      
      var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
      };
      
      fetch("http://csci5308vm24.research.cs.dal.ca:8085/afford/home/"+data.get('email'), requestOptions)
        .then(response => response.text())
        .then(function(result){
          if (result == "home"){
            console.log("ok");
            localStorage.setItem("email",data.get('email'));
            localStorage.setItem("Status","authenticated");
          window.location.href = "/search";
          }
          else{
            window.alert("Wrong Credentials")
          }
        })
        .catch(error => console.log("invalid request"));
    }

  

    };

  return (
    <div style ={myStyle}>
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign in
          </Typography>
          <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="email"
              label="Email Address"
              name="email"
              autoComplete="email"
              autoFocus
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
            />
            <FormControlLabel
              control={<Checkbox value="remember" color="primary" />}
              label="Remember me"
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign In
            </Button>
            <Grid container>
              <Grid item xs>
                <Link href="#" variant="body2">
                  Forgot password?
                </Link>
              </Grid>
              <Grid item>
                <Link href="/Signup" variant="body2">
                  {"Don't have an account? Sign Up"}
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
    </div>
  );
}