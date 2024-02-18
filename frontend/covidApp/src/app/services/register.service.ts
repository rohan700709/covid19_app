import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  url="/api/v1/auth"
  
  constructor(private http: HttpClient) { }
  registerUser(response: any){
    localStorage.setItem('userData',JSON.stringify(response));
    return this.http.post(`${this.url}/userRegistration`,response);
    //localStorage.setItem('userData',JSON.stringify(response));
  }
}
