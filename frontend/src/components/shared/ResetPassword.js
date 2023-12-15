import {
    Button,
    Grid,
    Paper,
    Snackbar,
    Alert
  } from "@mui/material";
  import React, { useState } from "react";
  import setBodyColor from "../../functions/setBodyColor";
  import logo from "../../images/logo2.png";
  import InputAdornment from "@material-ui/core/InputAdornment";
  import Visibility from "@mui/icons-material/Visibility";
  import VisibilityOff from "@mui/icons-material/VisibilityOff";
  import FormControl from "@mui/material/FormControl";
  import OutlinedInput from "@mui/material/OutlinedInput";
  import InputLabel from "@mui/material/InputLabel";
  import IconButton from "@mui/material/IconButton";
  import {useLocalState} from "../../util/useLocalState";
  import { useNavigate, Link } from "react-router-dom";
  import { useEffect, useRef } from "react";
  
  
  import AuthService from "../../util/auth.service"
  
  const ResetPassword = () => {
    const navigate = useNavigate();
    const paperStyle = {
      padding: 20,
      height: 250,
      width: 700,
      margin: "auto",
      backgroundColor: "#D9D9D9",
    };
  
    const btnstyle = {
      margin: "20px 0",
      backgroundColor: "#00101F",
    };
  
    const textfieldStyle = {
      margin: "1rem auto",
    };
  
    setBodyColor({ color: "#00101F" });
  
  
   
  
    const [email, setEmail] = useState("");
    const [showError,setShowError] = useState(false);
    const [alert, setAlert] = useState(false);
    
  
    const handleSubmit = (event) => {
      event.preventDefault();
      document.body.style.cursor='wait';
     
      AuthService.resetPassword(email).then(
        () => {
          document.body.style.cursor='default';
          setShowError(false);
          setAlert(true);
        },
        error => {
          document.body.style.cursor='default';
          setShowError(true);
        }
      )
  
    };
  
    const handleCloseAlert = () => {
      setAlert(false);
    };
    
  
    return (
      <>
      <Snackbar
        open={alert}
        autoHideDuration={6000}
        anchorOrigin={{
          vertical: "top",
          horizontal: "right",
        }}
        onClose={handleCloseAlert}
      >
        <Alert sx={{ width: "100%" }} severity={"success"}>
          Link za obnovu lozinke poslan je na Vašu email adresu.
        </Alert>
      </Snackbar>
      <div>
        
        <div
          style={{
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
            height: "100%",
            margin: "30px auto",
          }}
        >
          <img src={logo} alt="Logo" width={250} />
        </div>
  
        <Grid>
          <Paper elevation={8} style={paperStyle}>
            <div style={{ margin: "20px auto" }}>
              <h1 color="#00101F" style={{ padding: 0, margin: 0, fontFamily: 'Yantramanav' }}>
                Obnova lozinke
              </h1>
              <hr
                style={{
                  color: "black",
                  backgroundColor: "black",
                  borderColor: "black",
                  height: "1px",
                  flex: "1px",
                  padding: 0,
                  margin: 0,
                }}
              />
            </div>

            <form onSubmit={handleSubmit}>
            <FormControl style={{margin:0,marginTop:8}} fullWidth variant="outlined">
                <InputLabel>Email adresa</InputLabel>
                <OutlinedInput
                  id="email"
                  name="email"
                  type="email"
                  label="Email adresa"
                  onChange={(event) => setEmail(event.target.value)}
                />
                <span style={{display: showError ? "block" : "none",color:"#d32f2f",fontFamily: 'Yantramanav', marginTop:10,fontSize:'16px' }}>Ne postoji korisnik sa ovom email adresom.</span>
              </FormControl>
              <Link style={{ marginTop: 20, textDecoration: "none"}} to="/login">Povratak na prijavu</Link>
              <div style={{ display: "flex", justifyContent: "flex-end", padding:0, margin:0 }}>
                <Button
                  type="submit"
                  color="primary"
                  variant="contained"
                  style={{backgroundColor: "#00101F",marginTop:10,textTransform: 'none',fontFamily:"Yantramanav",fontWight:"500",fontSize:"18px"}}
                  fullWidth
                  sx={{ mt: 0, mb: 2 }}
                >
                  Pošalji
                </Button>
              </div>
            </form>
          </Paper>
        </Grid>
      </div>
      </>
    );
  };
  
  export default ResetPassword;