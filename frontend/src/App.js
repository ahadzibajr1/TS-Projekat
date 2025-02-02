import "./App.css";
import Login from "./components/shared/login";
import ChangePassword from "./components/shared/ChangePassword.js";
import Home from "./components/shared/home";
import PrivateRoute from "./components/shared/privateroute";
import { Routes, Route, useNavigate, useLocation } from "react-router-dom";
import { useEffect } from "react";
import CreateTicket from "./components/user/ticket-create/CreateTicket";
import TicketOverview from "./components/shared/ticket-overview/TicketOverview";
import NotFound from "./components/shared/NotFound";
import ManualCreateEdit from "./components/agent/manual-edit/ManualCreateEdit";
import ManualOverview from "./components/shared/manual-overview/ManualOverview";
import TicketList from "./components/shared/ticketlist";
import ReportCreate from "./components/agent/report-create/ReportCreate";
import ReportOverview from "./components/agent/report-overview/ReportOverview";
import ReportList from "./components/agent/report-list/ReportList";
import ManualList from "./components/agent/manual-list/ManualList";
import ForumList from "./components/shared/forum/ForumList";
import ForumCreateEdit from "./components/shared/forum/ForumCreate";
import ForumPostList from "./components/shared/forum/ForumPostList";
import authService from "./util/auth.service";
import ResetPassword from "./components/shared/ResetPassword.js"

function App() {
  const navigate = useNavigate();
  const location = useLocation();

  const validateToken = async()=> {
    var valid = await authService.validateToken();
    if(!valid) {
      console.log("invalid token")
      navigate("/login");
    }
  }

  useEffect(() => {
    if(location.pathname != "/login" && !location.pathname.includes("/reset-password")) {
      validateToken();
    }
  }, [location]);  


  return (
    <div>
      <Routes>
        <Route path="/" element={<PrivateRoute />}>
          <Route path="/" element={<Home></Home>}></Route>
          <Route
            path="/forumpost-list"
            element={<ForumPostList></ForumPostList>}
          ></Route>
          <Route
            path="/forum-list"
            element={<ForumList></ForumList>}
          ></Route>
          <Route
            path="/create-forum"
            element={<ForumCreateEdit></ForumCreateEdit>}
          ></Route>
          <Route
            path="/create-ticket"
            element={<CreateTicket></CreateTicket>}
          ></Route>
          <Route
            path="/ticket"
            element={<TicketOverview></TicketOverview>}
          ></Route>
          <Route
            path="/ticket-list"
            element={<TicketList></TicketList>}
          ></Route>
          <Route
            path="/manual/create"
            element={<ManualCreateEdit></ManualCreateEdit>}
          ></Route>
          <Route
            path="/manual/edit"
            element={<ManualCreateEdit></ManualCreateEdit>}
          ></Route>
          <Route
            path="/manual"
            element={<ManualOverview></ManualOverview>}
          ></Route>
          <Route
            path="/manual-list"
            element={<ManualList></ManualList>}
          ></Route>
           <Route
            path="/report/create"
            element={<ReportCreate></ReportCreate>}
          ></Route>
          <Route
            path="/report"
            element={<ReportOverview></ReportOverview>}
          ></Route>
           <Route
            path="/report-list"
            element={<ReportList></ReportList>}
          ></Route>
        </Route>
        <Route path="/login" element={<Login></Login>} />
        <Route path="*" element={<NotFound></NotFound>} />
        <Route path="/change-password" element={<ChangePassword></ChangePassword>} />
        <Route path="/reset-password/request" element={<ResetPassword></ResetPassword>} />
        <Route path="/reset-password" element={<ChangePassword></ChangePassword>} />

      </Routes>
    </div>
  );
}

export default App;
