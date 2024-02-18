import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url="/api/v1/auth/authenticate"
  constructor(private http: HttpClient) { }


  //calling the server 
  generateToken(credentials: any){
    return this.http.post(`${this.url}`,credentials);
  }






  //for login user with
  loginUser(response: any){
    localStorage.setItem('userData',JSON.stringify(response));
    return true;
  }

  //to check if logged in
  isLoggedIn(){
    let userDataFromLocalStorage = localStorage.getItem('userData');
    let userData = JSON.parse(userDataFromLocalStorage || '{}');
    let {token} = userData;
    if(token==undefined || token==='' || token===null){
      return false;
    }
    else{
      return true;
    }
  }

  //to logout
  logout(){
    localStorage.removeItem('userData');
    return true;
  }

  //to get token
  getUser(){
    let userDataFromLocalStorage = localStorage.getItem('userData');
    let userData = JSON.parse(userDataFromLocalStorage || '{}');
    //let {token,user} = userData;
    return userData;
  }
}