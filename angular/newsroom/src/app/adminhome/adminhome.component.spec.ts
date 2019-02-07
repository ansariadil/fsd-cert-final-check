import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { BrowserModule } from '@angular/platform-browser';
import { AdminhomeComponent } from './adminhome.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from '../app-routing.module';
import { SignupComponent } from '../signup/signup.component';
import { LoginComponent } from '../login/login.component';
import { HomeComponent } from '../home/home.component';
import { APP_BASE_HREF } from '@angular/common';
import { Routes } from '@angular/router';


describe('AdminhomeComponent', () => {
    let component: AdminhomeComponent;
    let fixture: ComponentFixture<AdminhomeComponent>;

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
        fixture = TestBed.createComponent(AdminhomeComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create Admin home Component', () => {
        expect(component).toBeTruthy();
    });
});
