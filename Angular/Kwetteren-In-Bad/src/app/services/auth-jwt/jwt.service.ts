import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Constants } from 'src/app/constants/api.consts';
import { Router } from '@angular/router';
import { Token } from 'src/app/models/Token';

@Injectable({
  providedIn: 'root'
})
export class JwtService {
  private redirectUrl: string;
  
  constructor(private httpClient: HttpClient, private router: Router) { }

  login (username: string, password: string) {
    this.httpClient.post(Constants.API_URL + '/Auth/login', {username, password}).toPromise().then((res: Token) => {
      localStorage.setItem(Constants.TOKEN, res.token);
      localStorage.setItem(Constants.LOCAL_ID, res.id);
      localStorage.setItem(Constants.LOCAL_USERNAME, res.username);
      if (this.redirectUrl) {
        this.router.navigate([this.redirectUrl]);
      } else {
        this.router.navigate(['/home'])
      }
      this.redirectUrl = null;
    });
  }

  logout() {
    localStorage.removeItem(Constants.TOKEN);
  }

  public get loggedIn(): boolean {
    return localStorage.getItem(Constants.TOKEN) !==  null;
  }

  public getRedirectUrl(): string {
    return this.redirectUrl;
  }

  public setRedirectUrl(url: string) {
    this.redirectUrl = url;
  }

  getToken(): string {
    return localStorage.getItem(Constants.TOKEN);
  }
}