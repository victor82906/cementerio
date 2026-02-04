import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { CementerioService } from '../../services/cementerio-service';
import { Footer } from '../footer/footer';
import { Cabecera } from '../cabecera/cabecera';
import { AuthService } from '../../services/auth-service';
import { ChangeDetectorRef } from '@angular/core';
import { ZonaService } from '../../services/zona-service';
import { Zona } from '../zona/zona';
import { ViewChild } from '@angular/core';
import { Cargando } from '../cargando/cargando';

@Component({
  selector: 'app-cementerio',
  imports: [Footer, Cabecera, Zona, Cargando],
  templateUrl: './cementerio.html',
  styleUrl: './cementerio.css',
})
export class Cementerio {

  id:number = 0;
  cementerio: any;
  @ViewChild('zona') zona!: Zona;
  cargando: boolean = false;


  // para que se dimensionen las zonas
  viewBoxMapa: string = '0 0 500 500';

  constructor(private zonaService: ZonaService, private cdr: ChangeDetectorRef, private route: ActivatedRoute, private router: Router, private cementerioService: CementerioService, public authService: AuthService){}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.id = params['id'];
    });

    this.cargarCementerio();
  }

  cargarCementerio(){
    this.cargando = true;
    this.cementerioService.findById(this.id).subscribe({
      next: (response) => {
        this.cementerio = response;
        this.zonaService.getZonasCementerio(this.id).subscribe({
          next: (zonasResponse) => {
            this.cementerio.zonas = zonasResponse;
            this.cargando = false;
            this.cdr.markForCheck();
          },
          error: (err) => {
            console.error(err);
            this.cargando = false;
          }
        });
      },
      error: (err) => {
        console.error(err);
            this.cargando = false;
      }
    });
  }

  tarifas(){
    this.router.navigate(['/gestionar-tarifas'], { queryParams: { idCementerio: this.id } });
  }

  gestionarZonas(){
    this.router.navigate(['/gestion-zonas'], { queryParams: { idCementerio: this.id } });
  }

  ajustaraMapa(evento: any){
    const img = evento.target;
    this.viewBoxMapa = `0 0 ${img.naturalWidth} ${img.naturalHeight}`;
    this.cdr.markForCheck();
  }

  abrirZona(idZona: number){
    this.zona.abrir(idZona);
  }
  
}
