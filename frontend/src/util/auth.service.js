import api from "./api";
import TokenService from "./token.service";
import bcrypt from 'bcryptjs';

class AuthService {

   hashPassword = async (password) => {
    try {
        const hashed = await bcrypt.hash(password, 10);
        return hashed;
    } catch (error) {
        console.error('Error hashing password:', error);
        throw error; // Rethrow the error to handle it at a higher level if needed
    }
  };

  async login(email, password) {
    //password =  await this.hashPassword(password);
    return api
      .post("/auth/login", {
        email,
        password
      })
      .then(response => {
        if (response.status===200) {
          TokenService.setUser(response.data);
          console.log(response.data)
        }

        return response.data;
      });
  }

  async changePassword(oldPassword, newPassword) {
    return api
      .put("/auth/change-password", {
        oldPassword,
        newPassword
      })
      .then(response => {
        if (response.status===200) {
          TokenService.removeUser();
          console.log(response.data)
        } else if (response.status===403){
          throw new Error("Neispravni podaci.");
        }
        return response.data;
      });
  }

  logout() {
    TokenService.removeUser();
  }



  getCurrentUser() {
    var user = TokenService.getUser();
    console.log(user);
    return user;
  }

  validateToken() {
    var token = TokenService.getLocalAccessToken();
    return api
      .get("/auth/validate-token?token=" + token)
      .then(response => {
        if (response.status===200) {
          return true;
        }

        return false;
      });
  }
  
}

export default new AuthService();