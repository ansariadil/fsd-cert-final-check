import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { environment } from './environment';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};
@Injectable({
  providedIn: 'root'
})
export class UserService {

  getAllUsers = `${environment.serviceUrlPrefix}/rest/user/getAllUser`;
  toggleUserUrl = `${environment.serviceUrlPrefix}/rest/user/toggleUser`;
  getUserByKeyUrl = `${environment.serviceUrlPrefix}/rest/user/userbyname/`;
  saveArticleUrl = `${environment.serviceUrlPrefix}/rest/article/saveArticle`;
  deleteArticleUrl = `${environment.serviceUrlPrefix}/rest/article/deleteArticle/`;
  getUserByEmailUrl = `${environment.serviceUrlPrefix}/rest/user/`;

  // saveUserArticleUrl1 = `${environment.serviceUrlPrefix}/article/saveUserArticle`;
  // saveUserArticleUrl = `${environment.serviceUrlPrefix}/article/getUser`;


  constructor(private http: HttpClient, private authService: AuthService) { }

  getAllUser(): Observable<any> {
    return this.http.get<any>(this.getAllUsers);
  }

  getUserByKey(keys): Observable<any> {
    console.log(this.getUserByKeyUrl + keys);
    return this.http.get<any>(this.getUserByKeyUrl + keys);
  }

  toggleUser(user): Observable<any> {
    console.log(user);
    return this.http.post<any>(this.toggleUserUrl, user, httpOptions);
  }

  saveArticle(json): Observable<any> {
    return this.http.post<any>(this.saveArticleUrl, json, httpOptions);
  }
  deleteArticle(json, email): Observable<any> {
    console.log(email);
    console.log(json);
    return this.http.post<any>(this.deleteArticleUrl + email, json, httpOptions);
  }
  getUserByEmail(email): Observable<any> {
    console.log(this.getUserByEmailUrl + email);
    return this.http.get<any>(this.getUserByEmailUrl + email);
  }

  // saveUserArticle(json): Observable<any> {
  //   console.log(JSON.stringify(json));
  //   return this.http.post<any>(this.saveUserArticleUrl, json, httpOptions);
  // }
  // saveUser(user): Observable<any> {
  //   console.log(user);
  //   return this.http.post<any>(this.saveUserArticleUrl1, user, httpOptions);
  // }

}
