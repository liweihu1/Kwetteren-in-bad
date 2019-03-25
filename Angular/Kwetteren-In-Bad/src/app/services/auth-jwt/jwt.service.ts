import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Constants } from 'src/app/constants/api.consts';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class JwtService {
  private redirectUrl: string;
  
  constructor(private httpClient: HttpClient, private router: Router) { }

  login (username: string, password: string) {
    this.httpClient.post(Constants.API_URL + '/Auth/login', {username, password}).toPromise().then((res: Token) => {
      console.log(res);
      localStorage.setItem('access_token', res.token);
      if (this.redirectUrl) {
        this.router.navigate([this.redirectUrl]);
      } else {
        this.router.navigate(['/home'])
      }
      this.redirectUrl = null;
    });
  }

  logout() {
    localStorage.removeItem('access_token');
  }

  public get loggedIn(): boolean {
    return localStorage.getItem('access_token') !==  null;
  }

  public getRedirectUrl(): string {
    return this.redirectUrl;
  }

  public setRedirectUrl(url: string) {
    this.redirectUrl = url;
  }

  getToken(): string {
    return localStorage.getItem('access_token');
  }
}

export interface Token {
  token: string;
}