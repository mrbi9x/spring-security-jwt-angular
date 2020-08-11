import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, AbstractControl, ValidatorFn, ValidationErrors, FormBuilder } from '@angular/forms';
import { SignupRequest } from '../dtos/signup-request';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupForm: FormGroup;
  signupRequest: SignupRequest;

  constructor(private fb: FormBuilder) {
    // this.signupRequest = {
    //   username: '',
    //   email: '',
    //   password: '',
    //   passwordConfirm: ''
    // };
    this.createSignupForm();
  }

  ngOnInit(): void {
  }

  get username() {
    return this.signupForm?.get('username');
  }
  get email() {
    return this.signupForm?.get('email');
  }
  get password() {
    return this.signupForm?.get('passwordConfirmGroup.password');
  }
  get passwordConfirm() {
    return this.signupForm?.get('passwordConfirmGroup.passwordConfirm');
  }

  private createSignupForm() {
    this.signupForm = this.fb.group({
      username: ['', [
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(50),
      ]],
      email: ['', {
        updateOn: 'blur', validators: [
          Validators.required,
          Validators.email
        ]
      }],
      passwordConfirmGroup: this.fb.group({
        password: ['', {
          validators: [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(255)
          ]
        }],
        passwordConfirm: ['', {
          updateOn: 'blur', validators: [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(255),
            // this.passwordConfirmValidator
          ]
        }]
      }, { validators: [this.passwordConfirmValidator] }),
    });
  }

  onSignup() {
    if (this.signupForm.invalid) {
      return;
    }
    console.log('On Signup');
    this.signupRequest = {
      username: this.username.value,
      email: this.email.value,
      password: this.password.value,
      passwordConfirm: this.passwordConfirm.value
    }
    console.log(this.signupRequest)
  }

  passwordConfirmValidator: ValidatorFn = (formGroup: FormGroup): ValidationErrors | null => {
    let pass = formGroup.get('password');
    let passConfirm = formGroup.get('passwordConfirm');
    return pass && passConfirm && pass.value === passConfirm.value ? null : { passwordNotMatch: true };
  }

}
