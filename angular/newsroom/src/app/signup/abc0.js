import { APP_BASE_HREF } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { HttpTestingController } from '@angular/common/http/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';
import { Observable } from 'rxjs';
import { AdminhomeComponent } from '../adminhome/adminhome.component';
import { AppRoutingModule } from '../app-routing.module';
import { HomeComponent } from '../home/home.component';
import { LoginService } from '../login.service';
import { LoginComponent } from '../login/login.component';
import { SignupComponent } from './signup.component';


fdescribe('SignupComponent', () => {
    let component: SignupComponent;
    let fixture: ComponentFixture<SignupComponent>;
    let loginService: any;
    let spy: any;

    beforeEach(async(() => {
        // const routes: Routes = [
        //     { path: "", component: LoginComponent },
        //     { path: "login", component: LoginComponent },
        //     { path: "signup", component: SignupComponent },
        //     { path: "home", component: HomeComponent },
        //     { path: "adminhome", component: AdminhomeComponent }
        // ];
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

    // beforeEach(() => {
    //     fixture = TestBed.createComponent(SignupComponent);
    //     component = fixture.componentInstance;
    //     fixture.detectChanges();
    // });

    // it('should create the signup component', () => {
    //     expect(component).toBeTruthy();
    // });

    it('should have more than one language', fakeAsync(() => {
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
        loginService = fixture.debugElement.injector.get(LoginService);
        spy = spyOn(loginService, 'getLanguageList')
            .and.returnValue(Observable.create(() => language));


        component.samplengOnIt();
        fixture.detectChanges();
        tick();
        expect(component.languageList).not.toBeNaN();
        expect(component.languageList).not.toBeNull();
        // expect(component.languageList).not.toBeUndefined();
    }));

    // it('should invalid when signup form is empty', fakeAsync(() => {
    //     const signupForm = {
    //         name: '',
    //         email: '',
    //         password: '',
    //         language: {
    //             id: ''
    //         }
    //     };
    //     tick();
    //     component.signup(signupForm);
    //     expect(component.signupSuccess).toBeFalsy();
    // }));

    // it('should invalid when name is empty', async(() => {
    //     const TEST_DATA = new FormGroup({
    //         name: new FormControl(''),
    //         email: new FormControl('adil'),
    //         password: new FormControl('adil'),
    //         language: new FormGroup({
    //             id: new FormControl('4')
    //         })
    //     });
    //     component.signupForm.setValue(TEST_DATA);
    //     expect(component.signupForm.valid).toBeFalsy();
    // }));

    // it('should invalid when email is empty', async(() => {
    //     const TEST_DATA = new FormGroup({
    //         name: new FormControl('adil'),
    //         email: new FormControl(''),
    //         password: new FormControl('adil'),
    //         language: new FormGroup({
    //             id: new FormControl('4')
    //         })
    //     });
    //     component.signupForm.setValue(TEST_DATA);
    //     expect(component.signupForm.valid).toBeFalsy();
    // }));
});
