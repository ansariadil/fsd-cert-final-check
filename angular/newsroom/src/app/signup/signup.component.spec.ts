import { APP_BASE_HREF } from '@angular/common';
import { HttpEvent, HttpEventType } from '@angular/common/http';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, fakeAsync, TestBed, inject, tick } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';
import { Observable, observable, of } from 'rxjs';
import { AdminhomeComponent } from '../adminhome/adminhome.component';
import { AppRoutingModule } from '../app-routing.module';
import { HomeComponent } from '../home/home.component';
import { LoginService } from '../login.service';
import { LoginComponent } from '../login/login.component';
import { SignupComponent } from './signup.component';
import { toObservable } from '@angular/forms/src/validators';


fdescribe('SignupComponent', () => {
    let component: SignupComponent;
    let fixture: ComponentFixture<SignupComponent>;
    let loginService: any;
    let spy: any;

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
                LoginService,
            ],
            schemas: [NO_ERRORS_SCHEMA]
        })
            .compileComponents();
        fixture = TestBed.createComponent(SignupComponent);
        component = fixture.componentInstance;
        loginService = fixture.debugElement.injector.get(LoginService);
        fixture.detectChanges();
    }));

    beforeEach(async(() => {
        loginService = fixture.debugElement.injector.get(LoginService);
        let data: any = JSON.parse(JSON.stringify(
            [
                { id: 1, code: "ar", name: "Arabic" },
                { id: 2, code: "de", name: "Deutsch" },
                { id: 3, code: "ud", name: "Urdu" },
                { id: 4, code: "en", name: "English" },
                { id: 5, code: "es", name: "Spanish" },
                { id: 7, code: "fr", name: "French" },
                { id: 8, code: "he", name: "Hebrew" },
                { id: 9, code: "it", name: "Italian" }
            ]
        ));
        spyOn(loginService, 'getLanguageList').and.returnValue(of(data));
    }));

    it('should create the signup component', () => {
        expect(component).toBeTruthy();
    });

    it('should get language when getLanguage method', async () => {
        component.ngOnInit();
        expect(component.languageList).not.toBeNull();
        expect(component.languageList).not.toBeNaN();
        expect(component.languageList).not.toBeUndefined();
    });

    it('should invalid when signup form is empty', () => {
        // const signupForm = {
        //     name: '',
        //     email: '',
        //     password: '',
        //     language: {
        //         id: ''
        //     }
        // };
        // let nativeElement = fixture.nativeElement;
        expect(component.signupSuccess).toBeTruthy();
        component.signup(component.signupForm);
        expect(component.signupSuccess).toBeFalsy();
        expect(fixture.debugElement.nativeElement);
    });

    // it('should invalid when signup form is empty', () => {
    //     component.signupForm.controls['name'].setValue('');
    //     component.signupForm.controls['email'].setValue('');
    //     component.signupForm.controls[''].setValue('');
    //     component.signupForm.controls['name'].setValue('');
    //     component.signup(component.signupForm);
    //     expect(component.signupSuccess).toBeFalsy();
    // });


});
