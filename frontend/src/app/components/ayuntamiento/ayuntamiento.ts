import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth-service';
import { Cabecera } from "../cabecera/cabecera";
import { Footer } from "../footer/footer";

@Component({
  selector: 'app-ayuntamiento',
  imports: [RouterLink, Cabecera, Footer],
  templateUrl: './ayuntamiento.html',
  styleUrl: './ayuntamiento.css',
})
export class Ayuntamiento {

  constructor(private router: Router, private authService: AuthService){}

}
