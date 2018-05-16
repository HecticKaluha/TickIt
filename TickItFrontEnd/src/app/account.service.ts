import {Injectable} from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class AccountService {

  public token: string;

  private postLoginURL = "http://localhost:8080/tickit/api/account/login";

  constructor(protected httpClient: HttpClient, private router: Router) {

  }

  public login(username: string, password: string) {
    let body = {username: username, password: password};
    this.httpClient.post(`${this.postLoginURL}`, body, {observe: 'response'}).subscribe(
      (res) => {
        let token = res.headers.get('Authorization');
        if (token != null) {
          this.token = token;
          localStorage.setItem('currentUser', JSON.stringify({username: username, token: token}));
          localStorage.setItem('loggedinuser', username);
          localStorage.setItem('token', token);
          this.router.navigateByUrl('/home/');
        }
      },
      err => {
        if (err.status == 401) {
          alert("Not logged in!");
          this.router.navigateByUrl('/home/');
        }
      }
    );
  }
  public getToken(): string {
    return localStorage.getItem('token');
  }
}
