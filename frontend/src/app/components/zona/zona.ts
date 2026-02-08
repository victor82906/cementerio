import { CommonModule } from '@angular/common';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { ZonaService } from '../../services/zona-service';
import { ParcelaService } from '../../services/parcela-service';
import { ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth-service';
import { ConcesionService } from '../../services/concesion-service';
declare var bootstrap: any;

@Component({
  selector: 'app-zona',
  imports: [CommonModule],
  templateUrl: './zona.html',
  styleUrl: './zona.css',
})
export class Zona {

  @ViewChild('modalElement') modalElement!: ElementRef;
  private modalInstance: any;

  zona: any;
  parcelas: any[] = [];
  parcelasLibres: any[] = [];
  concesionesCiudadano: any[] = [];
  cargando: boolean = false;

  constructor(private auth: AuthService, private zonaService: ZonaService, private parcelaService: ParcelaService, private cdr: ChangeDetectorRef, private router: Router, private concesionService: ConcesionService) { }

  abrir(idZona: number) {
    if (!this.modalInstance) {
      this.modalInstance = new bootstrap.Modal(this.modalElement.nativeElement);
    }

    this.zona = null; 
    this.parcelas = [];
    this.parcelasLibres = [];
    this.concesionesCiudadano = [];
    this.cargando = true;

    this.modalInstance.show();

    if(this.auth.getRol() == 'CIUDADANO'){
      this.concesionService.getConcesionesCiudadano(this.auth.getId()).subscribe({
        next: (response) => {
          this.concesionesCiudadano = response;
          this.cdr.markForCheck();
          console.log(this.concesionesCiudadano);
        },
        error: (err) => {
          console.error(err);
        }
      });
    }

    this.cargando = true;
    this.zonaService.getZona(idZona).subscribe({
      next: (response) => {
        this.zona = response;
        this.parcelaService.getParcelasZona(this.zona.id).subscribe({
          next: (response) => {
            this.parcelas = response;
            this.parcelaService.getParcelasLibresZona(this.zona.id).subscribe({
              next: (response) => {
                this.parcelasLibres = response;
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
      },
      error: (err) => {
        console.error(err);
        this.cargando = false;
      }
    });
  }

  cerrar() {
    this.modalInstance.hide();
  }

  esLibre(parcelaId: number): boolean {
    return this.parcelasLibres.some(p => p.id === parcelaId);
  }

  esMia(parcelaId: number): boolean {
    return this.concesionesCiudadano.some(c => c.parcela.id === parcelaId);
  }

  clickParcela(parcela: any) {

    if (this.esMia(parcela.id)) {
        this.modalInstance.hide();
        this.router.navigate(['/comprar-servicio'], { queryParams: { parcelaId: parcela.id } });
    } else if (this.esLibre(parcela.id)) {
        this.modalInstance.hide();
        if (this.auth.getRol() !== 'CIUDADANO' && this.auth.getRol() !== 'AYUNTAMIENTO' && this.auth.getRol() !== 'ADMIN'){
            this.modalInstance.hide();
            this.router.navigate(['/registro-ciudadano']);
        } else if(this.auth.getRol() === 'CIUDADANO') {
            this.modalInstance.hide();
            this.router.navigate(['/comprar-concesion'], { queryParams: { parcelaId: parcela.id, zonaId: this.zona.id } });
        }
    }
  }

}