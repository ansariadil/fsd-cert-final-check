import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NewsapiService } from '../newsapi.service';
import { AuthService } from '../auth.service';
import { UserService } from '../user.service';
import * as $ from './../../../node_modules/jquery/dist/jquery';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  news: any = [];
  language: string;
  searchKeys: any;
  newsSearched: string = 'top';
  dataCollected: boolean = false;
  clickToFav: boolean = false;
  favList: any[];
  // user:any;

  constructor(private router: Router, private authService: AuthService, private newsApi: NewsapiService,
    private userService: UserService) { }

  ngOnInit() {
    this.newsSearched = 'top';
    this.language = this.authService.getLanguage();
    this.newsApi.getArticlesByLanguage(this.language).subscribe(async data => {
      this.news = await data;
      this.dataCollected = true;
      console.log(this.news);
    });
  }

  addToFavorite(item) {
    // tslint:disable-next-line:prefer-const
    item["source"] = item.source.name;
    let user = {};
    user["email"] = this.authService.getEmail();
    user["article"] = [item];
    console.log(user);
    this.userService.saveArticle(user).subscribe(
      data => {
        console.log(data);
        alert("Added to your Favorites");
      }
    );
    this.clickToFav = !this.clickToFav;

    // // $('#experienceButton').removeClass('far').addClass('fas');
    // if (element) {

    // }
  }

  removeFromFavorite(item) {
    this.userService.deleteArticle(item, this.authService.getEmail()).subscribe(data => {
      alert("Added to your Favorites");
    });
  }

  getFavoriteArticle() {
    this.newsSearched = 'favorite';
    this.userService.getUserByEmail(this.authService.getEmail()).subscribe(data => {
      console.log(data.article);
      this.news = data.article;
      this.dataCollected = false;
    });
  }

  search(keys) {
    console.log(keys);
    this.newsSearched = 'search';
    this.searchKeys = keys;
    this.newsApi.getArticlesByKeyword(keys).subscribe(data => {
      this.news = data;
      console.log(this.news);
    });
  }

}
