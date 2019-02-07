import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import * as $ from 'jQuery';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LoginService]
})
export class LoginComponent implements OnInit {
  loginSuccess: boolean = true;

  loginForm = new FormGroup({
    email: new FormControl(
      '', [Validators.required, Validators.minLength(3)]
    ),
    password: new FormControl(
      '', [Validators.required, Validators.minLength(3)]
    )
  });

  constructor(private router: Router, private authService: AuthService, private loginService: LoginService) { }

  ngOnInit() {
  }
  submit(loginForm: FormGroup) {
    console.log(loginForm.value);
    if (this.loginForm.invalid) {
      this.loginSuccess = false;
      for (let i in loginForm.controls) {
        if (loginForm.controls[i] instanceof FormControl) {
          loginForm.controls[i].markAsTouched();
        }
      }
      return;
    }
    this.loginService.authenticateUser(loginForm.value).subscribe(
      data => {
        console.log("incoming Data: " + JSON.stringify(data));
        if (data.authenticated) {
          console.log("Login Successfully");
          this.authService.login();
          this.authService.setRole(data.user.role.name);
          this.authService.setEmail(data.user.email);
          this.authService.setLanguage(data.user.language.code);
          this.authService.setToken(data.token);
          console.log(this.authService.getUser());
          if (this.authService.getRole() === "AD") {
            this.router.navigate(['/adminhome']);
          } else {
            this.router.navigate(['/home']);
          }
        }
        else {
          console.log("Unsuccessfull Login");
          this.loginSuccess = false;
        }
      },
      error => {
        console.log(error);
        this.loginSuccess = false;
      }
    );
  }
}
