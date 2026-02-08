import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth-service';
import { Cabecera } from "../cabecera/cabecera";
import { Footer } from '../footer/footer';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AudioHome } from "../audio-home/audio-home";

@Component({
  selector: 'app-ciudadano',
  imports: [Cabecera, Footer, RouterLink, CommonModule, FormsModule, AudioHome],
  templateUrl: './ciudadano.html',
  styleUrl: './ciudadano.css',
})
export class Ciudadano {

  busqueda: string = '';

  constructor(private router: Router, private authService: AuthService){}

  parcelas(){
    this.router.navigate(['/parcelas-ciudadano'], { queryParams: { idCiudadano: this.authService.getId() } });
  }

  difuntos(){
    this.router.navigate(['/difuntos-ciudadano'], { queryParams: { idCiudadano: this.authService.getId() } });
  }

  buscar(){
    this.router.navigate(['/busqueda-cementerio'], { queryParams: { nombre: this.busqueda } });
  }

}
