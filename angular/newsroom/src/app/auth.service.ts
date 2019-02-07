import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  token: any;
  loggedIn = false;
  role: string;
  language: string;
  email: string;
  userArticle: any[] = [];
  user: any;

  constructor() { }

  login() {
    console.log("Inside auth service login()");
    this.loggedIn = true;
  }

  logout() {
    console.log("Inside auth service logout()");
    this.loggedIn = false;
    this.setRole(null);
    this.setLanguage(null);
    this.setEmail(null);
    this.setUserArticle(null);
  }
  getUserArticle() {
    return this.userArticle;
  }

  setUserArticle(userArticle) {
    this.userArticle = userArticle;
  }

  getUser() {
    return this.user;
  }

  setUser(user: string) {
    this.user = user;
  }

  getRole() {
    return this.role;
  }

  setRole(role: string) {
    this.role = role;
  }
  getLanguage() {
    return this.language;
  }

  setLanguage(language: string) {
    this.language = language;
  }

  getEmail() {
    return this.email;
  }

  setEmail(email: string) {
    this.email = email;
  }
  getToken() {
    return this.token;
  }

  setToken(token: string) {
    this.token = token;
  }

}
