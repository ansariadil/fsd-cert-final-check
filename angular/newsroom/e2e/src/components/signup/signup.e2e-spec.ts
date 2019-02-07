import { SignupPage } from './signup.po';
import { protractor, browser } from 'protractor';

describe('Signup page', () => {
    let page: SignupPage;
    const EC = protractor.ExpectedConditions;
    beforeEach(() => {
        page = new SignupPage();
        page.navigateToSignupPage();
    });

    it('should get success message when signup', () => {
        page.sendNameForSignup().sendKeys('testName');
        page.sendEmailForSignup().sendKeys('testEmail');
        page.sendPasswordForSignup().sendKeys('testPassword');
        page.sendLanguageIdForSignup().sendKeys("English");
        page.getSignupButton().click();
        browser.sleep(6000);
        const message = page.getSuccessMessage().getText();
        expect(message).toContain('Successfully Registered');
    });

    it('should get failed message when signup', () => {
        page.sendNameForSignup().sendKeys('testName');
        page.sendEmailForSignup().sendKeys('testEmail');
        page.sendPasswordForSignup().sendKeys('testPassword');
        page.sendLanguageIdForSignup().sendKeys("English");
        page.getSignupButton().click();
        browser.sleep(6000);
        const message = page.getErrorMessage().getText();
        expect(message).toContain('Email Already Exist');
    });
     /* it('should be not be able to login if email is wrong', async() => {
        page.sendEmailForLogin().sendKeys('sz@gmail.com');
        page.sendPasswordForLogin().sendKeys('A123456');
        page.getLoginButton().click();
        browser.wait(EC.visibilityOf(page.getErrorMessage()));
        expect(page.getErrorMessage().getText()).toBe('Invalid Email Id or Password.');
    });   */
});



// https://trailheadtechnology.com/ui-automation-testing-of-angular-apps-using-protractor-jasmine/
// https://scotch.io/@charlieoduk/angular-end-to-end-testing507


// --- Karma

// https://scotch.io/tutorials/testing-angular-with-jasmine-and-karma-part-1