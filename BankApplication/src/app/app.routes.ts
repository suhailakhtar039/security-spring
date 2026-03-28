import { Routes } from '@angular/router';
import { Body } from './body/body';
import { Login } from './login/login';
import { ContactUs } from './contact-us/contact-us';

export const routes: Routes = [
  { path: '', component: Body, pathMatch: 'full' },
  { path: 'login', component: Login, pathMatch: 'full' },
  { path: 'contact', component: ContactUs, pathMatch: 'full' },
];
