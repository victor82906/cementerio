import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth-service';
import { Cabecera } from "../cabecera/cabecera";
import { Footer } from '../footer/footer';

@Component({
  selector: 'app-ciudadano',
  imports: [Cabecera, Footer, RouterLink],
  templateUrl: './ciudadano.html',
  styleUrl: './ciudadano.css',
})
export class Ciudadano {

  constructor(private router: Router, private authService: AuthService){}

  parcelas(){
    this.router.navigate(['/parcelas-ciudadano'], { queryParams: { idCiudadano: this.authService.getId() } });
  }

  difuntos(){
    this.router.navigate(['/difuntos-ciudadano'], { queryParams: { idCiudadano: this.authService.getId() } });
  }

}
