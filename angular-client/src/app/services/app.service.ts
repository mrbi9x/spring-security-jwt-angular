import { Injectable } from '@angular/core';
import { LoginRequest } from '../dtos/login-request';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AppService {
  BASE_URL = 'http://localhost:8080';
  constructor(private httpClient: HttpClient) {}

  doSignin(loginRequest: LoginRequest) {
    return this.httpClient.post(this.BASE_URL + '/signin', loginRequest);
  }
}
