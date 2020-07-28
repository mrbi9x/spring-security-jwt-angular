import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from '../services/app.service';
import { LoginRequest } from '../dtos/login-request';
import { TokenResponse } from '../dtos/token-response';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css'],
})
export class SigninComponent implements OnInit {
  signinForm: FormGroup;

  constructor(private appService: AppService) {}

  ngOnInit(): void {
    this.signinForm = new FormGroup({
      username: new FormControl('', [
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(50),
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(4),
      ]),
    });
  }

  onSignin() {
    console.log(this.signinForm.value);
    if (this.signinForm.invalid) {
      this.signinForm.updateValueAndValidity();
      return;
    }
    const loginReq: LoginRequest = {
      username: this.signinForm.controls.username.value,
      password: this.signinForm.controls.password.value,
    };
    this.appService.doSignin(loginReq).subscribe((data: TokenResponse) => {
      console.log(data);
    });
  }
}
