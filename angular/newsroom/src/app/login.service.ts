import { Injectable } from '@angular/core';
import { environment } from './environment';
import { Observable } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  loginUrl = `${environment.serviceUrlPrefix}/login/authenticate`;
  languageUrl = `${environment.serviceUrlPrefix}/language/list`;
  registerUrl = `${environment.serviceUrlPrefix}/register/user`;

  constructor(private http: HttpClient) { }

  authenticateUser(json): Observable<any> {
    return this.http.post<any>(this.loginUrl, json, httpOptions);

  }

  getLanguageList(): Observable<any> {
    return this.http.get(this.languageUrl);
  }

  registerUser(json): Observable<any> {
    return this.http.post<any>(this.registerUrl, json, httpOptions);
  }
}
