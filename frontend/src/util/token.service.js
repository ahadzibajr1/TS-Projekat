import { jwtDecode } from "jwt-decode";

class TokenService {
    getLocalRefreshToken() {
      const refreshToken = localStorage.getItem("refresh-token");
      return refreshToken;
    }
  
    getLocalAccessToken() {
      const accessToken = localStorage.getItem("access-token");
      return accessToken;
    }
  
    updateLocalAccessToken(token) {
      localStorage.setItem("access-token", token);
    }
  
    getUser() {
      var token = this.getLocalAccessToken();
      if(token) {
        try {
          return jwtDecode(token);
        } catch(e) {
          return null;
        }
      }
      return null;
    }
  
    setUser(user) {
      localStorage.setItem("access-token", user.accessToken);
      localStorage.setItem("refresh-token", user.refreshToken);
    }
  
    removeUser() {
      localStorage.removeItem("access-token");
      localStorage.removeItem("refresh-token");
    }
  }
  
  export default new TokenService();