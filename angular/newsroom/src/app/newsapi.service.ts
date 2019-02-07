import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NewsapiService {

  api_key = '2e43ff500324430792ff19744782b10c';

  constructor(private http: HttpClient) { }

  getArticlesByLanguage(language): Observable<any> {
    return this.http.get
    (`https://newsapi.org/v2/top-headlines?language=${language}&pageSize=25&country=in&apiKey=${this.api_key}`);
  }

  getArticlesByKeyword(keys) {
    return this.http.get
    (`https://newsapi.org/v2/everything?q=${keys}&sortBy=publishedAt&pageSize=25&apiKey=${this.api_key}`);
  }

}

// ar Arabic
// de Deutsch
// en English
// es Spanish; Castilian
// fr French
// he Hebrew
// it Italian
// nl Dutch
// no Norwegian
// pt Portuguese
// ru Russian
// se Northern Sami
// ud Urdu
// zh Chinese
