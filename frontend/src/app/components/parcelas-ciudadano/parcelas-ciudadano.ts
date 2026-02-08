import { Component, ViewChild } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ConcesionService } from '../../services/concesion-service';
import { ChangeDetectorRef } from '@angular/core';
import { Cabecera } from '../cabecera/cabecera';
import { Footer } from '../footer/footer';
import { Cargando } from '../cargando/cargando';
import { ParcelaService } from '../../services/parcela-service';
import { ZonaService } from '../../services/zona-service';
import { ModalConfirmar } from '../modal-confirmar/modal-confirmar';
import { Router } from '@angular/router';
import { Ciudadano } from '../ciudadano/ciudadano';
import { ModalError } from '../modal-error/modal-error';

@Component({
  selector: 'app-parcelas-ciudadano',
  imports: [Cabecera, Footer, Cargando, RouterLink, ModalConfirmar, ModalError],
  templateUrl: './parcelas-ciudadano.html',
  styleUrl: './parcelas-ciudadano.css',
})
export class ParcelasCiudadano {

  concesiones: any[] = [];
  cargando: boolean = false;
  idCiudadano: number = 0;
  @ViewChild("modalConfirmar") modalConfirmar!: ModalConfirmar;
  @ViewChild("modalError") modalError!: ModalError;
  idConcesionABorrar: number | null = null;

  constructor(private route: ActivatedRoute, private concesionService: ConcesionService, private cdr: ChangeDetectorRef, private parcelaService: ParcelaService, private zonaService: ZonaService, private router: Router) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.idCiudadano = params['idCiudadano'];
    });

    this.cargarConcesiones();
  }

  cargarConcesiones() {
    this.cargando = true;
    this.concesionService.getConcesionesCiudadano(this.idCiudadano).subscribe({
      next: (response) => {
        this.concesiones = response;
        this.cdr.markForCheck();
        console.log(this.concesiones);
        this.cargaZonaCementerio();
      },
      error: (err) => {
        console.error(err);
        this.cargando = false;
        this.cdr.markForCheck();
      }
    });
  }

  cargaZonaCementerio(){
    if(this.concesiones.length == 0){
      this.cargando = false;
      this.cdr.markForCheck();
    }
    this.concesiones.forEach(concesion => {
      this.parcelaService.getZona(concesion.parcela.id).subscribe({
        next: (zona) => {
          concesion.zona = zona;
          this.cdr.markForCheck();
          this.zonaService.getCementerio(concesion.zona.id).subscribe({
            next: (cementerio) => {
              concesion.cementerio = cementerio;
              this.cargando = false;
              this.cdr.markForCheck();
              console.log(this.concesiones);
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
    });
  }

  comprarServicio(parcelaId: number, cementerioId: number) {
    this.router.navigate(['/comprar-servicio'], { queryParams: { parcelaId: parcelaId } });
  }

  borrarConcesion(concesionId: number) {
    this.idConcesionABorrar = concesionId;
    this.modalConfirmar.abrir();
  }

  confirmarBorrado(){
    if(this.idConcesionABorrar !== null){
      this.concesionService.borrar(this.idConcesionABorrar).subscribe({
        next: (response) => {
          this.modalError.abrirModal("Exito", "Concesion dada de baja, se procedera a la exhumacion de sus difuntos.", false);
          this.cargarConcesiones();
        },
        error: (err) => {
          console.error(err);
        }
      });
    }
  }

}
