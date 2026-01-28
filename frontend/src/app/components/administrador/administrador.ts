import { Component } from '@angular/core';
import { AuthService } from '../../services/auth-service';
import { Cabecera } from "../cabecera/cabecera";
import { Footer } from '../footer/footer';

@Component({
  selector: 'app-administrador',
  imports: [Cabecera, Footer],
  templateUrl: './administrador.html',
  styleUrl: './administrador.css',
})
export class Administrador {

  constructor(private auth: AuthService){}



}
