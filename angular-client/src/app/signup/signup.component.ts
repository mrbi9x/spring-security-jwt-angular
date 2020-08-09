import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, AbstractControl, ValidatorFn, ValidationErrors } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupForm: FormGroup;

  constructor() { }

  ngOnInit(): void {
    this.signupForm = new FormGroup({
      username: new FormControl('', [
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(50),
        Validators.pattern('[a-zA-Z0-9._]')
      ]),
      email: new FormControl('', [
        Validators.required,
        Validators.email
      ]),
      passwordConfirmGroup: new FormGroup({
        password: new FormControl('', [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(255)
        ]),
        passwordConfirm: new FormControl('', [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(255),
        ])
      }, { validators: this.passwordConfirmValidator }),
    });
  }

  onSignup() {
    console.log('On Signup');
    // TODO
  }

  passwordConfirmValidator: ValidatorFn = (ac: AbstractControl): ValidationErrors | null => {
    let pass = ac.get('password');
    let passConfirm = ac.get('passwordConfirm');
    return pass && passConfirm && pass === passConfirm ? null : { passwordMatch: false };
  }

}
