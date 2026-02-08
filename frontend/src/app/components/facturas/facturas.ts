import { Component } from '@angular/core';
import { FacturaService } from '../../services/factura-service';
import { AuthService } from '../../services/auth-service';
import { ChangeDetectorRef } from '@angular/core';
import { Cabecera } from '../cabecera/cabecera';
import { Footer } from '../footer/footer';
import { Cargando } from '../cargando/cargando';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-facturas',
  imports: [Cabecera, Footer, Cargando, CommonModule],
  templateUrl: './facturas.html',
  styleUrl: './facturas.css',
})
export class Facturas {

  facturas: any[] = [];
  cargando: boolean = false;

  constructor(private facturaService: FacturaService, private auth: AuthService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.cargando = true;
    if(this.auth.getRol() == "AYUNTAMIENTO"){
      this.facturaService.getAyuntamiento(this.auth.getId()).subscribe({
        next: (response) => {
          this.facturas = this.ordenarPorFecha(response);
          this.cargando = false;
          this.cdr.markForCheck();
        },
        error: (err) => {
          console.error(err);
          this.cargando = false;
          this.cdr.markForCheck();
        }
      });
    }else if(this.auth.getRol() == "CIUDADANO"){
      this.facturaService.getCiudadano(this.auth.getId()).subscribe({
        next: (response) => {
          this.facturas = this.ordenarPorFecha(response);
          this.cargando = false;
          this.cdr.markForCheck();
        },
        error: (err) => {
          console.error(err);
          this.cargando = false;
        }
      });
    }else{
      this.cargando = false;
      this.cdr.markForCheck();
    }
  }

  ordenarPorFecha(facturas: any[]){
    return facturas.sort((a, b) => new Date(b.fecha).getTime() - new Date(a.fecha).getTime());
  }

  descarga(id: number){
    this.facturaService.descargaFactura(id);
  }

}
