import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth-service';
import { ZonaService } from '../../services/zona-service';
import { ParcelaService } from '../../services/parcela-service';
import { PagoService } from '../../services/pago-service';
import { ConcesionService } from '../../services/concesion-service';
import { ModalError } from '../modal-error/modal-error';
import { ViewChild } from '@angular/core';
import { Cabecera } from '../cabecera/cabecera';
import { Footer } from '../footer/footer';
import { ChangeDetectorRef } from '@angular/core';
import { Cargando } from '../cargando/cargando';


@Component({
  selector: 'app-comprar-concesion',
  imports: [ModalError, ModalError, Cabecera, Footer, Cargando],
  templateUrl: './comprar-concesion.html',
  styleUrl: './comprar-concesion.css',
})
export class ComprarConcesion {

  zona: any;
  parcela: any;
  ayuntamiento: any;
  ciudadano: any;
  factura: any;
  pagoExitoso: boolean = false;
  @ViewChild('modalError') modalError!: ModalError;
  cargando: boolean = false;

  constructor(private cdr: ChangeDetectorRef, private route: ActivatedRoute, private auth: AuthService, private zonaService: ZonaService, private parcelaService: ParcelaService, private pagoService: PagoService, private concesionService: ConcesionService) { }

  ngOnInit(): void {
    let zonaId = 0;
    let parcelaId = 0;
    this.route.queryParams.subscribe(params => {
      zonaId = params['zonaId'];
      parcelaId = params['parcelaId'];
    });

    this.ciudadano = this.auth.getUser();
    this.cargarDatos(zonaId, parcelaId);
  }

  cargarDatos(zonaId: number, parcelaId: number) {
    this.cargando = true;
    this.zonaService.getZona(zonaId).subscribe({
      next: (response) => {
        this.zona = response;
        this.cdr.markForCheck();
      },
      error: (err) => {
        console.error(err);
        this.cargando = false;
      }
    });

    this.parcelaService.getParcela(parcelaId).subscribe({
      next: (response) => {
        this.parcela = response;
        this.cdr.markForCheck();
      },
      error: (err) => {
        console.error(err);
        this.cargando = false;
      }
    });

    this.parcelaService.getAyuntamiento(parcelaId).subscribe({
      next: (response) => {
        this.ayuntamiento = response;
        this.cargando = false;
        this.cdr.markForCheck();
      },
      error: (err) => {
        console.error(err);
        this.cargando = false;
      }
    });
  }

  pagar() {
    const concesion = {
      precio: this.zona.precio,
      ciudadanoId: this.ciudadano.id
    }
    this.concesionService.guardarConcesion(this.parcela.id, concesion).subscribe({
      next: (response) => {
        this.factura = response;
        console.log(this.factura);
        this.pagoExitoso = true;
        this.modalError.abrirModal("Exito", "Concesion creada correctamente, descargue su factura", false);

      },
      error: (err) => {
        console.error(err);
        this.pagoExitoso = false;
        this.modalError.abrirModal("Error", err.error, true);
      }
    });
  }

}
