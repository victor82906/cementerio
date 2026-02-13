import { Component } from '@angular/core';
import { AuthService } from '../../services/auth-service';
import { Cabecera } from "../cabecera/cabecera";
import { Footer } from '../footer/footer';
import { RouterLink } from '@angular/router';
import { AudioHome } from "../audio-home/audio-home";
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-administrador',
  imports: [Cabecera, Footer, RouterLink, AudioHome, CommonModule, FormsModule],
  templateUrl: './administrador.html',
  styleUrl: './administrador.css',
})
export class Administrador {

  busqueda: string = '';

  constructor(private auth: AuthService, private router: Router){}

  buscar(){
    this.router.navigate(['/busqueda-cementerio'], { queryParams: { nombre: this.busqueda } });
  }

}
