import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-contact-us',
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatInputModule,
  ],
  templateUrl: './contact-us.html',
  styleUrl: './contact-us.css',
})
export class ContactUs {
  contactUsForm!: FormGroup;

  constructor(private fb: FormBuilder) {
    this.contactUsForm = this.fb.group({
      name: [''],
      email: ['', [Validators.email]],
      subject: [''],
      message: [''],
    });
  }

  onSubmitofContactUs() {
    console.log(this.contactUsForm.value);
  }
}
