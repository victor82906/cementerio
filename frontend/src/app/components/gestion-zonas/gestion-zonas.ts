import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
import { Cabecera } from "../cabecera/cabecera";
import { Footer } from '../footer/footer';
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';
import { ModalError } from "../modal-error/modal-error";
import { ViewChild } from '@angular/core';
import { ModalConfirmar } from '../modal-confirmar/modal-confirmar';
import { ZonaService } from '../../services/zona-service';
import { Cargando } from '../cargando/cargando';


@Component({
  selector: 'app-gestion-zonas',
  imports: [Cabecera, Footer, CommonModule, ReactiveFormsModule, ModalError, ModalConfirmar, Cargando],
  templateUrl: './gestion-zonas.html',
  styleUrl: './gestion-zonas.css',
})
export class GestionZonas {

  idCementerio: number = 0;
  formZona!: FormGroup;
  tipoZonas: any[] = [];
  zonas: any[] = [];
  idZonaABorrar: number | null = null;
  @ViewChild(ModalError) modalError!: ModalError;
  @ViewChild('modalConfirmar') modalConfirmar!: ModalConfirmar;

  cargando: boolean = true;

  constructor(private cdr: ChangeDetectorRef, private route: ActivatedRoute, private zonasService: ZonaService) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.idCementerio = params['idCementerio'];
      this.cargarDatos();
    });

    this.formZona = new FormGroup({
      tipoZona: new FormControl('', Validators.required),
      precio: new FormControl('', [Validators.required, Validators.min(0.1)]),
      capacidadParcelas: new FormControl('', [Validators.required, Validators.min(1)]),
      filas: new FormControl('', [Validators.required, Validators.min(1)]),
      columnas: new FormControl('', [Validators.required, Validators.min(1)]),
      coordenadas: new FormControl('', Validators.required)
    });
  }

  cargarDatos() {
    this.cargando = true;
    this.zonasService.getTiposZona().subscribe({
      next: (response) => {
        this.tipoZonas = response;
        this.cargando = false;
        this.cdr.markForCheck();
      },
      error: (err) => {
        console.error(err);
        this.cargando = false;
      }
    });
    this.zonasService.getZonasCementerio(this.idCementerio).subscribe({
      next: (response) => {
        this.zonas = response;
        this.cargando = false;
        this.cdr.markForCheck();
      },
      error: (err) => {
        console.error(err);
        this.cargando = false;
      }
    });
  }

  guardarZona() {
    if (this.formZona.valid) {
      const nuevaZona = {
        tipoZona: this.tipoZonas.find(t => t.id == this.formZona.value.tipoZona),
        precio: this.formZona.value.precio,
        capacidadParcelas: this.formZona.value.capacidadParcelas,
        filas: this.formZona.value.filas,
        columnas: this.formZona.value.columnas,
        coordenadas: this.formZona.value.coordenadas
      };
      this.zonasService.crearZona(this.idCementerio, nuevaZona).subscribe({
        next: (response) => {
          this.zonas.push(response);
          this.modalError.abrirModal("Exito", "Zona creada correctamente", false);
          this.formZona.reset();
          this.cdr.markForCheck();
        },
        error: (err) => {
          console.error(err);
          this.modalError.abrirModal("Error", err.error, true);
        }
      });
    }else{
      this.formZona.markAllAsTouched();
    }
  }

  borrarZona(idZona: number){
    this.idZonaABorrar = idZona;
    this.modalConfirmar.abrir();
  }

  confirmarBorrado(){
    if(this.idZonaABorrar !== null){
      this.zonasService.borrarZona(this.idZonaABorrar).subscribe({
        next: (response) => {
          console.log(response);
          this.cargarDatos();
        },
        error: (err) => {
          console.error(err);
        }
      });
    }
  }

  actualizarPrecio(zona: any, nuevoPrecio: string){
    const precioNum = parseFloat(nuevoPrecio);
    if(!isNaN(precioNum) && precioNum >= 0){
      zona.precio = precioNum;
      zona.modificado = true;
    }
  }

  actualizarCapacidad(zona: any, nuevaCapacidad: string){
    const capacidadNum = parseInt(nuevaCapacidad);
    if(!isNaN(capacidadNum) && capacidadNum >= 1){
      zona.capacidadParcelas = capacidadNum;
      zona.modificado = true;
    }
  }


  guardarCambioZona(zona: any){
    const zonaActualizada = {
      id: zona.id,
      tipoZona: zona.tipoZona,    
      filas: zona.filas,
      columnas: zona.columnas,
      coordenadas: zona.coordenadas,
      precio: zona.precio,
      capacidadParcelas: zona.capacidadParcelas
    };
    this.zonasService.actualizarZona(zona.id, zonaActualizada).subscribe({
      next: (response) => {
        console.log(response);
        this.modalError.abrirModal("Exito", "Zona actualizada correctamente", false);
        zona.modificado = false;
        this.cdr.markForCheck();
      },
      error: (err) => {
        console.error(err);
        this.modalError.abrirModal("Error", err.error, true);
      }
    });
  }


}
