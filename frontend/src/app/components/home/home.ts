import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth-service';
import { Cabecera } from '../cabecera/cabecera';
import { Footer } from '../footer/footer';

@Component({
  selector: 'app-home',
  imports: [FormsModule, CommonModule, Cabecera, Footer],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  
  busqueda: string = '';

  constructor(private router: Router, private auth: AuthService) {}

  ngOnInit(): void {
    if(this.auth.getRol() == "AYUNTAMIENTO"){
      this.router.navigate(['/ayuntamiento']);
    }else if(this.auth.getRol() == "CIUDADANO"){
      this.router.navigate(['/ciudadano']);
    } else if(this.auth.getRol() == "ADMIN"){
      this.router.navigate(['/administrador']);
    }
  }

  buscar(){
    if(this.busqueda.trim()){
      this.router.navigate(['/busqueda-cementerio'], { queryParams: { nombre: this.busqueda } });
    }
  }
}
