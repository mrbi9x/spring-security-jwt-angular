import { Injectable } from '@angular/core';
import { SigninRequest } from '../dtos/signin-request';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AppService {
  BASE_URL = 'http://localhost:8080';
  constructor(private httpClient: HttpClient) { }

  doSignin(SigninRequest: SigninRequest) {
    return this.httpClient.post(this.BASE_URL + '/signin', SigninRequest);
  }
}
