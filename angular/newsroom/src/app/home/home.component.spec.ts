import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeComponent } from './home.component';
import { Routes } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { AdminhomeComponent } from '../adminhome/adminhome.component';
import { SignupComponent } from '../signup/signup.component';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from '../app-routing.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { APP_BASE_HREF } from '@angular/common';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async(() => {
    const routes: Routes = [
        { path: "", component: LoginComponent },
        { path: "login", component: LoginComponent },
        { path: "signup", component: SignupComponent },
        { path: "home", component: HomeComponent },
        { path: "adminhome", component: AdminhomeComponent }
    ];
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
            { provide: APP_BASE_HREF, useValue: '/' }
        ]
    })
        .compileComponents();
}));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create home component', () => {
    expect(component).toBeTruthy();
  });
});
