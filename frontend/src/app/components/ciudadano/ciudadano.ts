import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth-service';
import { Cabecera } from "../cabecera/cabecera";
import { Footer } from '../footer/footer';

@Component({
  selector: 'app-ciudadano',
  imports: [Cabecera, Footer],
  templateUrl: './ciudadano.html',
  styleUrl: './ciudadano.css',
})
export class Ciudadano {

  constructor(private router: Router, private authService: AuthService){}

}
