import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


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

@Injectable()
export class LoginServiceMock {
    constructor() { }



    getLanguageList(): Observable<any> {
        return Observable.create(language);
    }
}
