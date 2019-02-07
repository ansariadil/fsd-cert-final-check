import { APP_BASE_HREF } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AdminhomeComponent } from '../adminhome/adminhome.component';
import { AppRoutingModule } from '../app-routing.module';
import { HomeComponent } from '../home/home.component';
import { LoginService } from '../login.service';
import { SignupComponent } from '../signup/signup.component';
import { LoginComponent } from './login.component';


describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let nativeElements: any;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
          BrowserModule,
          HttpClientModule,
          FormsModule,
          ReactiveFormsModule,
          AppRoutingModule,
      ],
      declarations: [
          SignupComponent,
          LoginComponent,
          HomeComponent,
          AdminhomeComponent
      ],
      providers: [
          { provide: APP_BASE_HREF, useValue: '/' },
          LoginService
      ]
  })
      .compileComponents();
}));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent); //Create the componant in the test
    component = fixture.componentInstance; //to access componant ts file
    nativeElements = fixture.nativeElement;
    fixture.detectChanges();
  });

  it('should create the login componant', () => {
    expect(component).toBeDefined();
  });

  it('should create a `FormGroup` comprised of `FormControl`s', () => {
    component.ngOnInit();
    expect(component.loginForm instanceof FormGroup).toBe(true);
  });

  it('should return invalid when form is empty', async(() => {
    component.loginForm.controls['email'].setValue('');
    component.loginForm.controls['password'].setValue('');
    expect(component.loginForm.valid).toBeFalsy();
  }));

  it('should return invalid when email length is less than 3', async(() => {
    component.loginForm.controls['email'].setValue('ad');
    component.loginForm.controls['password'].setValue('adil');
    expect(component.loginForm.valid).toBeFalsy();
  }));

  it('should return invalid when password length is less than 3', async(() => {
    component.loginForm.controls['email'].setValue('adil');
    component.loginForm.controls['password'].setValue('ad');
    expect(component.loginForm.valid).toBeFalsy();
  }));

  it('form should be invalid', () => {
    component.loginForm.controls['email'].setValue('');
    component.loginForm.controls['password'].setValue('');
    expect(component.loginForm.valid).toBeFalsy();
  });

  it('form should be valid', () => {
    component.loginForm.controls['email'].setValue('');
    component.loginForm.controls['password'].setValue('');
    expect(component.loginForm.valid).toBeFalsy();
  });

  // it('should return true when click on sign in button', () => {
  //   component.loginForm = new FormGroup({
  //     email: new FormControl('adil'),
  //     password: new FormControl("adil")
  //   });
  //   // component.ngOnInit();

  //   // tslint:disable-next-line:max-line-length
  //   let spy = spyOn(loginService, `authenticateUser`).and.returnValue(
  //     'output'
  //   );
  //   component.submit(component.loginForm);


  // });

});
