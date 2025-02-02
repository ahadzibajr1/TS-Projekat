import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { useEffect } from "react";
import api from "../../../util/api";
import Header from "../../shared/header";
import NotFound from "../../shared/NotFound";
import { Container, Paper } from "@mui/material";
import ReportIcon from "@mui/icons-material/Report";
import { i18n } from "dateformat";
import Backdrop from "@mui/material/Backdrop";
import CircularProgress from "@mui/material/CircularProgress";
import TopInfo from "./TopInfo";
import Description from "./Description";
import History from "./History";
import authService from "../../../util/auth.service";
import UnauthorizedAccess from "../UnauthorizedAccess"
import RelatedTicketsBind from '../../agent/home/RelatedTicketModals';
import {Breadcrumbs,Stack,Typography} from "@mui/material";
import Link from '@mui/material/Link';
import NavigateNextIcon from '@mui/icons-material/NavigateNext';

i18n.dayNames = [
  "Ned",
  "Pon",
  "Uto",
  "Sri",
  "Čet",
  "Pet",
  "Sub",
  "Nedjelja",
  "Ponedjeljak",
  "Utorak",
  "Srijeda",
  "Četvrtak",
  "Petak",
  "Subota",
];

i18n.monthNames = [
  "Jan",
  "Feb",
  "Mar",
  "Apr",
  "Maj",
  "Jun",
  "Jul",
  "Aug",
  "Sep",
  "Okt",
  "Nov",
  "Dec",
  "Januar",
  "Februar",
  "Mart",
  "April",
  "Maj",
  "Juni",
  "July",
  "August",
  "Septembar",
  "Oktobar",
  "Novembar",
  "Decembar",
];

function TicketOverview() {
  const location = useLocation();
  var mounted = false;
  const searchParams = new URLSearchParams(location.search);
  const id = searchParams.get("id");

  const [ticket, setTicket] = useState();
  const [ticketComments, setTicketComments] = useState();

  const navigate = useNavigate();

  const breadcrumbs = [
    <Link underline="hover" key="1" color="#00101f" href="/" onClick={(e)=>{e.preventDefault(); navigate("/ticket-list")}}>
      Pregled zahtjeva
    </Link>,
    <Typography key="3" color="text.primary">
      {ticket? ticket.title : ""}
    </Typography>,
  ];

  useEffect(() => {
    if (!mounted && location.state && id === location.state.id) {
      mounted = true;
      setTicket(location.state);
      api.get("/ticket-comment/ticket/" + id).then((res) => {
        setTicketComments(res.data);
      });
    } else if (!mounted) {
      mounted = true;
      loadTicketData();
      if (id != null)
        api.get("/ticket-comment/ticket/" + id).then((res) => {
          setTicketComments(res.data);
        });
    }
  }, []);

  const loadTicketData = () => {
    if (id != null) {
      api.get("/ticket/" + id).then((res) => {
        setTicket(res.data);
      });
    }
  };

  const user = authService.getCurrentUser();
  return (
    <div>
      <Header></Header>
      <div style={{marginLeft:"10%", marginTop:"30px"}}>
      <Stack spacing={2}>
      <Breadcrumbs
        separator={<NavigateNextIcon style={{color:"#00101f"}} fontSize="small" />}
        aria-label="breadcrumb"
      >
        {breadcrumbs}
      </Breadcrumbs>
      </Stack>
        </div>
      {id == null ? (
        <NotFound></NotFound>
      ) : (user.Role=="sd_user" && ticket!=null && ticket.createdBy.id!=user.id)
      || (user.Role=="sd_agent" && ticket!=null && ticket.assignedTo!=null && ticket.assignedTo.id!=user.id) ?(
        <UnauthorizedAccess></UnauthorizedAccess>
      ) : (
        <>
          {(ticket != null) & (ticketComments != null) ? (
            <Container
              sx={{ mt: 2 }}
              style={{ backgroundColor: "#F5F5F5", padding: 0, width: "80%" }}
            >
              <TopInfo ticket={ticket} setTicket={setTicket} setTicketComments={setTicketComments} ticketComments={ticketComments}/>
              <Description description={ticket.description} />
              <History ticketComments={ticketComments} ticketId={ticket.id} status={ticket.status} />
            </Container>
          ) : (
            <Backdrop
              sx={{ color: "#fff", zIndex: (theme) => theme.zIndex.drawer + 1 }}
              open={true}
            >
              <CircularProgress color="inherit" />
            </Backdrop>
          )}
        </>
      )}
    </div>
  );
}

export default TicketOverview;
