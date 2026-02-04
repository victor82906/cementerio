import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cabecera } from '../cabecera/cabecera';
import { Footer } from '../footer/footer';
import { CommonModule } from '@angular/common';
import { DifuntoService } from '../../services/difunto-service';
import { ChangeDetectorRef } from '@angular/core';
import { Cargando } from '../cargando/cargando';

@Component({
  selector: 'app-difuntos-ciudadano',
  imports: [Cabecera, Footer, CommonModule, Cargando],
  templateUrl: './difuntos-ciudadano.html',
  styleUrl: './difuntos-ciudadano.css',
})
export class DifuntosCiudadano {

  difuntos: any[] = [];
  idCiudadano: number = 0;
  cargando: boolean = false;

  constructor(private route: ActivatedRoute, private router: Router, private cdr: ChangeDetectorRef, private difuntoService: DifuntoService) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.idCiudadano = params['idCiudadano'];
    });
    this.cargarDifuntos();
  }

  cargarDifuntos() {
    this.cargando = true;
    this.difuntoService.getDifuntosCiudadano(this.idCiudadano).subscribe({
      next: (response) => {
        this.difuntos = response;
        this.cdr.markForCheck();
        this.cargaParcelas();
      },
      error: (err) => {
        console.error(err);
        this.cargando = false;
        this.cdr.markForCheck();
      }
    });
  }

  cargaParcelas() {
    this.difuntos.forEach(difunto => {
      this.difuntoService.getParcelaDifunto(difunto.id).subscribe({
        next: (parcela) => {
          difunto.parcela = parcela;
          this.cargando = false;
          this.cdr.markForCheck();
        },
        error: (err) => {
          console.error(err);
          this.cargando = false;
          this.cdr.markForCheck();
        }
      });
    });
    console.log(this.difuntos);
  }

  gestionarParcela(parcelaId: number) {
    this.router.navigate(['/comprar-servicio'], { queryParams: { parcelaId: parcelaId } });
  }


}
