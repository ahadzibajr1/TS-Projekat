import React from 'react';
import ReportProblemIcon from '@mui/icons-material/ReportProblem';
import FeedbackIcon from '@mui/icons-material/Feedback';
import StyleIcon from '@mui/icons-material/Style';
import ForumIcon from '@mui/icons-material/Forum';
import LiveHelpIcon from '@mui/icons-material/LiveHelp';
import { Link } from 'react-router-dom';
import Stack from '@mui/material/Stack';
import IconButton from '@mui/material/IconButton';

function Menu() {
  return (
    <div style={{marginLeft:"50%",width:"60%",marginTop:"17%",position:"absolute",backgroundColor:"inherit"}}>
    <Stack direction="column"
    justifyContent="flex-start"
    alignItems="flex-start"
    spacing={3}>
    <div>
    <IconButton size='large' sx={{ boxShadow: 5 }} style={{backgroundColor:"white"}}>
      <ReportProblemIcon style={{color:"#00101F"}}/>
    </IconButton>
    <Link style={{paddingLeft:20,fontFamily:"Yantramanav",fontSize:"100%",color:"#00101F",textDecoration:"none" }}>Prijavi problem</Link>
    </div>
    <div>
    <IconButton size='large' sx={{ boxShadow: 5 }} style={{backgroundColor:"white"}}>
      <FeedbackIcon style={{color:"#00101F"}}/>
    </IconButton>
    <Link style={{paddingLeft:20,fontFamily:"Yantramanav",fontSize:"100%",color:"#00101F",textDecoration:"none" }}>Podnesi zahtjev za uslugom</Link>
    </div>
    <div>
    <IconButton size='large' sx={{ boxShadow: 5 }} style={{backgroundColor:"white"}}>
      <StyleIcon style={{color:"#00101F"}}/>
    </IconButton>
    <Link style={{paddingLeft:20,fontFamily:"Yantramanav",fontSize:"100%",color:"#00101F",textDecoration:"none" }}>Pregledaj zahtjeve</Link>
    </div>
    <div>
    <IconButton size='large' sx={{ boxShadow: 5 }} style={{backgroundColor:"white"}}>
      <ForumIcon style={{color:"#00101F"}}/>
    </IconButton>
    <Link style={{paddingLeft:20,fontFamily:"Yantramanav",fontSize:"100%",color:"#00101F",textDecoration:"none" }}>Forum</Link>
    </div>
    <div>
    <IconButton size='large' sx={{ boxShadow: 5 }} style={{backgroundColor:"white"}}>
      <LiveHelpIcon style={{color:"#00101F"}}/>
    </IconButton>
    <Link style={{paddingLeft:20,fontFamily:"Yantramanav",fontSize:"100%",color:"#00101F",textDecoration:"none" }}>Pomoć</Link>
    </div>
    </Stack>
    </div>
    
  )
}

export default Menu