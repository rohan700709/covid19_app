import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../services/login.service';
//import Swal from 'sweetalert2'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  form!: FormGroup;
  status = false;
  credentials = {
    email:'',
    password: '',
  };
  constructor(private loginService: LoginService,
    // private headerService: HeaderService,
    private router: Router,
    private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.form = new FormGroup({
      email: new FormControl(this.credentials.email, [
        Validators.required,
        Validators.email,
      ]),
      password: new FormControl(this.credentials.password, [Validators.required,Validators.minLength(8),
        Validators.pattern(
          '(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-zd$@$!%*?&].{8,}'
        )])
    });
  }

  onSubmit() {
    //console.log('form is submitted');
    if (
      this.credentials.email != '' &&
      this.credentials.password != '' &&
      this.credentials.email != null &&
      this.credentials.password != null
    ) {
      console.log('email and password are not empty');
      //generate token
      this.loginService.generateToken(this.credentials).subscribe((response: any)=>{
        console.log(response);
        this.loginService.loginUser(response);
        //window.location.href = '/home';
        this.router.navigate((['/home']), { relativeTo: this.route });
      },error=>{
        console.log(error);
        if(error.status==500){
          this.status = true;
        }
        
      });
      // Swal.fire('', " Login Successfully",'success');
      

    }else{
      console.log('username and password are empty');
   
       }
  }

  formReset(){
    this.status=false;
  }
}