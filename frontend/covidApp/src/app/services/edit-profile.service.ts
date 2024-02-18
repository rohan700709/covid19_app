import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EditProfileService {

  url="/api/v1/auth/userUpdate";
  constructor(private http: HttpClient) { }

  editUser(response: any, uData: any) {
    localStorage.setItem('userData',JSON.stringify(uData));
    return this.http.post(this.url,response);
  }

  getUser(){
    let userDataFromLocalStorage = localStorage.getItem('userData');
    let userData = JSON.parse(userDataFromLocalStorage || '{}');
    //let {token,user} = userData;
    return userData;
  }
}
