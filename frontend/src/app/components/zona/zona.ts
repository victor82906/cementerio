import { CommonModule } from '@angular/common';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { ZonaService } from '../../services/zona-service';
import { ParcelaService } from '../../services/parcela-service';
import { ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth-service';
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
  cargando: boolean = false;

  constructor(private auth: AuthService, private zonaService: ZonaService, private parcelaService: ParcelaService, private cdr: ChangeDetectorRef, private router: Router) { }

  abrir(idZona: number) {
    if (!this.modalInstance) {
      this.modalInstance = new bootstrap.Modal(this.modalElement.nativeElement);
    }

    this.zona = null; 
    this.parcelas = [];
    this.cargando = true;

    this.modalInstance.show();

    this.cargando = true;
    this.zonaService.getZona(idZona).subscribe({
      next: (response) => {
        this.zona = response;
        this.parcelaService.getParcelasZona(this.zona.id).subscribe({
          next: (response) => {
            this.parcelas = response;
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

  cerrar() {
    this.modalInstance.hide();
  }

  clickParcela(parcela: any) {
    this.modalInstance.hide();
    if (this.auth.getRol() !== 'CIUDADANO') {
      this.router.navigate(['/registro-ciudadano']);
    } else {
      this.router.navigate(['/comprar-concesion'], { queryParams: { parcelaId: parcela.id, zonaId: this.zona.id } });
    }
  }

}
