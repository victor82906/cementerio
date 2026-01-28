import { Component } from '@angular/core';
import { AuthService } from '../../services/auth-service';
import { Router, RouterLink } from '@angular/router';
import { LoginService } from '../../services/login';

@Component({
  selector: 'app-cabecera',
  imports: [RouterLink],
  templateUrl: './cabecera.html',
  styleUrl: './cabecera.css',
})
export class Cabecera {

  constructor(private auth: AuthService, private router: Router, private login: LoginService) {}

  isAuth(): boolean {
    return this.auth.isAuth();
  }

  perfil(): void {
    if(this.auth.getRol() == "AYUNTAMIENTO"){
      this.router.navigate(['/editar-ayuntamiento'], {queryParams: {id: this.auth.getId()}});
    }else if(this.auth.getRol() == "CIUDADANO"){
      this.router.navigate(['/editar-ciudadano'], {queryParams: {id: this.auth.getId()}});
    }
  }

  logout(): void {
    this.login.logout();
    this.router.navigate(['/home']);
  }

}
