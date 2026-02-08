import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth-service';
import { Cabecera } from '../cabecera/cabecera';
import { Footer } from '../footer/footer';
import { ElementRef, ViewChild } from '@angular/core';
import { ChangeDetectorRef } from '@angular/core';
import { AudioHome } from '../audio-home/audio-home';
import { Meta, Title } from '@angular/platform-browser';

@Component({
  selector: 'app-home',
  imports: [FormsModule, CommonModule, Cabecera, Footer, AudioHome],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  
  busqueda: string = '';

  constructor(private router: Router, private auth: AuthService, private cdr: ChangeDetectorRef, private titulo: Title, private meta: Meta) {}

  ngOnInit(): void {
    this.configurarSeo();
    if(this.auth.getRol() == "AYUNTAMIENTO"){
      this.router.navigate(['/ayuntamiento']);
    }else if(this.auth.getRol() == "CIUDADANO"){
      this.router.navigate(['/ciudadano']);
    } else if(this.auth.getRol() == "ADMIN"){
      this.router.navigate(['/administrador']);
    }
  }

  buscar(){
    this.router.navigate(['/busqueda-cementerio'], { queryParams: { nombre: this.busqueda } });
  }

  configurarSeo() {
    this.titulo.setTitle('Inicio - Un lugar en el cielo | Gestión de Cementerios');
    this.meta.updateTag({ name: 'description', content: 'Encuentre y gestione espacios de descanso con dignidad. Localice difuntos, gestione lápidas y trámites de cementerio online.' });
    this.meta.updateTag({ name: 'keywords', content: 'cementerio, gestión cementerios, lápidas, difuntos, ayuntamiento, servicios funerarios' });
    this.meta.updateTag({ name: 'author', content: 'Víctor Molina Ruiz' });
  }

}
