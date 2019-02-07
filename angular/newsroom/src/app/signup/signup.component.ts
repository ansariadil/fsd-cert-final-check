import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { LoginService } from '../login.service';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  getList: any;
  signupStatus: any = {
    "emailExist": "true",
    "message": ""
  };
  signupSuccess: boolean = true;
  signupForm = new FormGroup({
    name: new FormControl(
      '', [Validators.required, Validators.minLength(3)]
    ),
    email: new FormControl(
      '', [Validators.required, Validators.minLength(3)]
    ),
    password: new FormControl(
      '', [Validators.required, Validators.minLength(3)]
    ),
    language: new FormGroup({
      id: new FormControl(
        '', [Validators.required]
      )
    })
  });

  loginForm = new FormGroup({
    email: new FormControl(
      '', [Validators.required, Validators.minLength(3)]
    ),
    password: new FormControl(
      '', [Validators.required]
    )
  });

  languageList: any;
  constructor(private loginService: LoginService, private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.loginService.getLanguageList().subscribe(data => {
      this.languageList = data;
      console.log(data);
    });
    // console.log(this.loginService.getLanguageList().subscribe());
  }

  setAsTouched(group: FormGroup | FormArray) {
    group.markAsTouched();
    for (let i in group.controls) {
      if (group.controls[i] instanceof FormControl) {
        group.controls[i].markAsTouched();
      } else {
        this.setAsTouched(group.controls[i]);
      }
    }
  }

  signup(signupForm) {
    if (this.signupForm.invalid) {
      this.signupSuccess = false;
      this.setAsTouched(signupForm);
      return;
    }
    console.log("inside signup");
    console.log(signupForm.value);
    this.loginService.registerUser(signupForm.value).subscribe(data => {
      this.signupSuccess = false;
      this.signupStatus = data;
      console.log(this.signupStatus);
    });
  }

  submit(loginForm) {
    console.log(loginForm.value);
    this.loginService.authenticateUser(loginForm.value).subscribe(
      data => {
        console.log("incoming Data: " + JSON.stringify(data));
        if (data.authenticated) {
          console.log("Login Successfully");
          this.authService.login();
          this.authService.setRole(data.user.role.name);
          this.authService.setEmail(data.user.email);
          this.authService.setLanguage(data.user.language.code);

          if (this.authService.getRole() === "AD") {
            this.router.navigate(['/adminhome']);
          } else {
            this.router.navigate(['/home']);
          }
        } else {
          console.log("Unsuccessfull Login");
        }
      },
      error => {
        console.log(error);
      }
    );
  }

}
