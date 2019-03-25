import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { JwtService } from 'src/app/services/auth-jwt/jwt.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm = new FormGroup ({
    username:  new FormControl(''),
    password: new FormControl('')
  });

  constructor(private jwtService: JwtService) { }

  ngOnInit() {
  }

  login() {
    this.jwtService.login(this.loginForm.get('username').value, this.loginForm.get('password').value);
  }
}
