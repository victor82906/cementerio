import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth-service';
import { Cabecera } from "../cabecera/cabecera";
import { Footer } from "../footer/footer";
import { AudioHome } from '../audio-home/audio-home';

@Component({
  selector: 'app-ayuntamiento',
  imports: [RouterLink, Cabecera, Footer, AudioHome],
  templateUrl: './ayuntamiento.html',
  styleUrl: './ayuntamiento.css',
})
export class Ayuntamiento {

  constructor(private router: Router, private authService: AuthService){}

  cementerios(){
    this.router.navigate(['/cementerios-ayuntamiento'], { queryParams: { idAyuntamiento: this.authService.getId() } });
  }

  servicios(){
    this.router.navigate(['/servicios-ayuntamiento'], { queryParams: { idAyuntamiento: this.authService.getId() } });
  }

}
