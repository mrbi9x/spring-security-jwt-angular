import { ValidatorFn, FormGroup, ValidationErrors } from '@angular/forms';

/* Password in signup form must be same */
export const PasswordConfirmMatchValidator: ValidatorFn = (control: FormGroup): ValidationErrors | null => {
    const name = control.get('password');
    const alterEgo = control.get('passwordConfirm');

    return name && alterEgo && name.value === alterEgo.value ? null : { passwordMatch: false };
};