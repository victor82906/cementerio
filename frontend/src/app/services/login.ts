import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserLogin } from '../components/login/userLogin';


@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private urlToken = 'http://localhost:8081/auth/login';
  private urlUsuario = 'http://localhost:8081/auth/who';

  constructor(private http: HttpClient) {}

  getTokenFromServer(usuario: UserLogin): Observable<any> {
    return this.http.post(this.urlToken, usuario);
  }
  
  getUsuarioFromServer(token: string): Observable<any> {
    const headers = { 'Authorization': `Bearer ${token}` };
    return this.http.get(this.urlUsuario, {headers});
  }

  login(usuario: any, token: any) {
    localStorage.setItem('usuario', JSON.stringify(usuario));
    localStorage.setItem('auth_token', token);
  }

  logout(): void {
    localStorage.removeItem('auth_token');
    localStorage.removeItem('usuario');
  }

}
