import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  public loggedIn = true;
  userData = localStorage.getItem('userData');
  user = JSON.parse(this.userData || '{}').userName;
   

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
    this.loggedIn = this.loginService.isLoggedIn();
  }

  logoutUser(){
    this.loginService.logout();
    location.reload();
  }  
 
  loginClicked(){
    this.loggedIn = this.loginService.isLoggedIn();
  }

  

}
