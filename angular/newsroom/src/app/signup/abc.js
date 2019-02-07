import { APP_BASE_HREF } from '@angular/common';
import { HttpClientModule, HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { async, ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule, FormGroup, FormControl } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { Routes } from '@angular/router';
import { AdminhomeComponent } from '../adminhome/adminhome.component';
import { AppRoutingModule } from '../app-routing.module';
import { HomeComponent } from '../home/home.component';
import { LoginComponent } from '../login/login.component';
import { UserService } from '../user.service';
import { SignupComponent } from './signup.component';
import { LoginService } from '../login.service';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterTestingModule } from '@angular/router/testing';
import { map } from 'rxjs/operators';
import { Observable, pipe } from 'rxjs';
import { environment } from '../environment';


fdescribe('SignupComponent', () => {
    let component: SignupComponent;
    let fixture: ComponentFixture<SignupComponent>;
    let loginService: any;
    let spy: any;
    let httpClient: HttpClient;
    let httpTestingController: HttpTestingController;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                BrowserModule,
                HttpClientModule,
                FormsModule,
                ReactiveFormsModule,
                AppRoutingModule,
                RouterTestingModule.withRoutes([
                    { path: "", component: LoginComponent },
                    { path: "login", component: LoginComponent },
                    { path: "signup", component: SignupComponent },
                    { path: "home", component: HomeComponent },
                    { path: "adminhome", component: AdminhomeComponent }
                ]),
                HttpClientTestingModule
            ],
            declarations: [
                SignupComponent,
                LoginComponent,
                HomeComponent,
                AdminhomeComponent
            ],
            providers: [
                { provide: APP_BASE_HREF, useValue: '/' },
                // { provide: LoginService, useClass: LoginServiceMock }
                LoginService
            ],
            schemas: [NO_ERRORS_SCHEMA]
        })
            .compileComponents();
        fixture = TestBed.createComponent(SignupComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    }));

    it('should have more than one language', () => {
        const language = {
            data: [
                { id: 1, code: "ar", name: "Arabic" },
                { id: 2, code: "de", name: "Deutsch" },
                { id: 3, code: "ud", name: "Urdu" },
                { id: 4, code: "en", name: "English" },
                { id: 5, code: "es", name: "Spanish" },
                { id: 7, code: "fr", name: "French" },
                { id: 8, code: "he", name: "Hebrew" },
                { id: 9, code: "it", name: "Italian" }
            ]
        };
        // Make an HTTP GET request
        httpClient = TestBed.get(HttpClient);
        httpTestingController = TestBed.get(HttpTestingController);
        httpClient.get<any>(`${environment.serviceUrlPrefix}/language/list`)
            .subscribe(data => {
                // When observable resolves, result should match test data
                expect(data).toEqual(language);
                console.log(data);
            }
            );

    });

});
