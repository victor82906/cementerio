import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ServicioService } from '../../services/servicio-service';
import { Cabecera } from '../cabecera/cabecera';
import { Footer } from '../footer/footer';
import { Cargando } from '../cargando/cargando';
import { ChangeDetectorRef } from '@angular/core';
import { ParcelaService } from '../../services/parcela-service';
import { ZonaService } from '../../services/zona-service';
import { ModalError } from '../modal-error/modal-error';
import { ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-servicios-ayuntamiento',
  imports: [Cabecera, Footer, Cargando, ModalError, CommonModule],
  templateUrl: './servicios-ayuntamiento.html',
  styleUrl: './servicios-ayuntamiento.css',
})
export class ServiciosAyuntamiento {

  idAyuntamiento: number = 0;
  servicios: any[] = [];
  serviciosPendientes: any[] = [];
  serviciosRealizados: any[] = [];
  cargando: boolean = false;
  @ViewChild("modalError") modalError!: ModalError;

  constructor(private route: ActivatedRoute, private servicioService: ServicioService, private cdr: ChangeDetectorRef, private parcelaService: ParcelaService, private zonaService: ZonaService) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.idAyuntamiento = params['idAyuntamiento'];
    });
    this.cargarServicios();
  }

  cargarServicios() {
    this.serviciosPendientes = [];
    this.serviciosRealizados = [];
    this.cargando = true;
    this.servicioService.getServiciosAyuntamiento(this.idAyuntamiento).subscribe({
      next: (response) => {
        this.servicios = response;
        this.cdr.markForCheck();
        this.cargaZonaCementerio();;
      },
      error: (err) => {
        console.error(err);
        this.cargando = false;
        this.cdr.markForCheck();
      }
    });
  }

  cargaZonaCementerio() {
    if(this.servicios.length == 0){
      this.cargando = false;
      this.cdr.markForCheck();
    }
    this.servicios.forEach(servicio => {
      if(servicio.realizado){
        this.serviciosRealizados.push(servicio);
      }else{
        this.serviciosPendientes.push(servicio);
      }
      this.parcelaService.getZona(servicio.parcela.id).subscribe({
        next: (zona) => {
          servicio.zona = zona;
          this.cdr.markForCheck();
          this.zonaService.getCementerio(servicio.zona.id).subscribe({
            next: (cementerio) => {
              servicio.cementerio = cementerio;
              this.cargando = false;
              this.cdr.markForCheck();
            },
            error: (err) => {
              console.error(err);
              this.cargando = false;
              this.cdr.markForCheck();
            }
          });
        },
        error: (err) => {
          console.error(err);
          this.cargando = false;
          this.cdr.markForCheck();
        }
      });
    });
  }

  marcarRealizado(servicioId: number) {
    this.servicioService.marcarRealizado(servicioId).subscribe({
      next: (response) => {
        this.modalError.abrirModal("Exito", "Servicio realizado correctamente", false);
        this.cargarServicios();
      },
      error: (err) => {
        this.modalError.abrirModal("Error", err.error, true);
        console.error(err);
      }
    });
  }


}
