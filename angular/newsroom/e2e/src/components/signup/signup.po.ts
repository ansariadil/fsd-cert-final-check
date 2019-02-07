import { browser, by, element, promise, ElementFinder, ElementArrayFinder } from 'protractor';

export class SignupPage {

    navigateToSignupPage() {
        return browser.get('/signup');
    }

    sendNameForSignup() {
        return element(by.id('name'));
    }

    sendEmailForSignup() {
        return element(by.id('email'));
    }

    sendPasswordForSignup() {
        return element(by.id('password'));
    }

    sendLanguageIdForSignup() {
        return element(by.id('languageId'));
    }

    getSignupButton() {
        return element(by.id('signup-button'));
    }

    getErrorMessage() {
        return element(by.className('alert alert-danger'));
    }

    getSuccessMessage() {
        return element(by.className('alert alert-success'));
    }

}
