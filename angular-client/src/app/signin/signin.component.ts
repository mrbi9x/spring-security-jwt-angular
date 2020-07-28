import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css'],
})
export class SigninComponent implements OnInit {
  signinForm: FormGroup;

  constructor() {}

  ngOnInit(): void {
    this.signinForm = new FormGroup({
      username: new FormControl('', [
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(50),
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
      ]),
    });
  }

  onSignin() {
    console.log(this.signinForm.value);
    if (this.signinForm.invalid) {
      this.signinForm.updateValueAndValidity();
      return;
    }
  }
}
