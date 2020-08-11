import { Injectable } from '@angular/core';
import { SigninRequest } from '../dtos/signin-request';
import { HttpClient } from '@angular/common/http';
import { SignupRequest } from '../dtos/signup-request';

@Injectable({
  providedIn: 'root',
})
export class AppService {
  BASE_URL = 'http://localhost:8080';
  constructor(private httpClient: HttpClient) { }

  doSignin(SigninRequest: SigninRequest) {
    return this.httpClient.post(this.BASE_URL + '/auth/signin', SigninRequest);
  }

  doSignup(signupRequest: SignupRequest) {
    return this.httpClient.post(this.BASE_URL + '/auth/signup', signupRequest);
  }
}
