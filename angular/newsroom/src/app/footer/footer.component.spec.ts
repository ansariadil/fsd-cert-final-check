import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FooterComponent } from './footer.component';
import { Routes } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { SignupComponent } from '../signup/signup.component';
import { HomeComponent } from '../home/home.component';
import { AdminhomeComponent } from '../adminhome/adminhome.component';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from '../app-routing.module';
import { APP_BASE_HREF } from '@angular/common';
import { AppComponent } from '../app.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('FooterComponent', () => {
  let component: FooterComponent;
  let fixture: ComponentFixture<FooterComponent>;

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
            AdminhomeComponent,
            AppComponent,
            FooterComponent
        ],
        schemas: [NO_ERRORS_SCHEMA],
        providers: [
            { provide: APP_BASE_HREF, useValue: '/' }
        ]
    })
        .compileComponents();
}));

  beforeEach(() => {
    fixture = TestBed.createComponent(FooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create Footer Component', () => {
    expect(component).toBeTruthy();
  });
});
