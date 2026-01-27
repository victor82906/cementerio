import { Injectable } from '@angular/core';
import { Usuario } from '../entities/usuario';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  
  getUser(): Usuario{
    return JSON.parse(localStorage.getItem('usuario') || '{}');
  }

  getNombre(): string{
    return this.getUser().nombre;
  }

  getRol(){
    return this.getUser().rol;
  }

  getToken(): string | null {
    return localStorage.getItem('auth_token');
  }

}
