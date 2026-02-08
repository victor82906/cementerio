import { Component } from '@angular/core';
import { AuthService } from '../../services/auth-service';
import { Cabecera } from "../cabecera/cabecera";
import { Footer } from '../footer/footer';
import { RouterLink } from '@angular/router';
import { AudioHome } from "../audio-home/audio-home";

@Component({
  selector: 'app-administrador',
  imports: [Cabecera, Footer, RouterLink, AudioHome],
  templateUrl: './administrador.html',
  styleUrl: './administrador.css',
})
export class Administrador {

  constructor(private auth: AuthService){}

}
