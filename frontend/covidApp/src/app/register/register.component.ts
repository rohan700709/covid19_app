import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RegisterService } from '../services/register.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form!: FormGroup; 
  credentials={
    userName:"",
    email:"",
    password:""
  }
  constructor(private registerService: RegisterService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {this.form = new FormGroup({
    userName: new FormControl(this.credentials.userName, [
      Validators.required, Validators.minLength(4),
    ]),
    email: new FormControl(this.credentials.email, [
      Validators.required,
      Validators.email,
    ]),
    password: new FormControl(this.credentials.password, [
      Validators.required,
      Validators.minLength(8),
      Validators.pattern(
        '(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-zd$@$!%*?&].{8,}'
      )
     
    ]),
  });
  }


  onSubmit(){
    if (
      this.credentials.email != '' &&
      this.credentials.password != '' &&
      this.credentials.email != null &&
      this.credentials.password != null
    )
    this.registerService.registerUser(this.credentials).subscribe((response: any)=>{
      
      
      console.log(response);
      
      this.registerService.registerUser(response);
      this.router.navigate((['/home']), { relativeTo: this.route });
      
      });
      Swal.fire(this.credentials.userName ," Registration Successful",'success');
  }

}
