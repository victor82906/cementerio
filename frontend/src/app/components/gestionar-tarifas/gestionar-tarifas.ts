import { Component } from '@angular/core';
import { Cabecera } from '../cabecera/cabecera';
import { CommonModule } from '@angular/common';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Footer } from '../footer/footer';
import { ActivatedRoute } from '@angular/router';
import { CementerioService } from '../../services/cementerio-service';
import { FormControl, Validators } from '@angular/forms';
import { ServicioService } from '../../services/servicio-service';
import { ModalConfirmar } from '../modal-confirmar/modal-confirmar';
import { ViewChild } from '@angular/core';
import { ModalError } from "../modal-error/modal-error";
import { ChangeDetectorRef } from '@angular/core';
import { TarifaService } from '../../services/tarifa-service';
import { Cargando } from '../cargando/cargando';

@Component({
  selector: 'app-gestionar-tarifas',
  imports: [Cabecera, Footer, CommonModule, ReactiveFormsModule, ModalConfirmar, ModalError, Cargando],
  templateUrl: './gestionar-tarifas.html',
  styleUrl: './gestionar-tarifas.css',
})
export class GestionarTarifas {

  idCementerio: number = 0;
  serviciosDisponibles: any[] = [];
  tarifas: any[] = [];
  serviciosRestantes: any[] = [];
  formTarifa!: FormGroup;
  servicioSeleccionado: any = null;
  idTarifaABorrar: number | null = null;
  @ViewChild('modalConfirmar') modalConfirmar!: ModalConfirmar;
  @ViewChild(ModalError) modalError!: ModalError;

  cargando: boolean = true;


  constructor(private cdr: ChangeDetectorRef, private route: ActivatedRoute, private servicioService: ServicioService, private tarifaService: TarifaService) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.idCementerio = params['idCementerio'];
      this.cargarDatos();
    });

    this.formTarifa = new FormGroup({
      tipoServicio: new FormControl('', Validators.required),
      precio: new FormControl('', [Validators.required, Validators.min(0)])
    });
  }

  cargarDatos(){
    this.cargando = true;
    this.tarifaService.getTarifasCementerio(this.idCementerio).subscribe({
      next: (response) => {
        this.tarifas = response;
        this.servicioService.getAllTipoServicios().subscribe({
          next: (serviciosResponse) => {
            this.serviciosDisponibles = serviciosResponse;
            this.serviciosRestantes = this.serviciosDisponibles.filter(sd => !this.tarifas.some(t => t.tipoServicio.id === sd.id));
          
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

  servicioCambia(){
    const servicioId = this.formTarifa.get('tipoServicio')?.value;
    this.servicioSeleccionado = this.serviciosRestantes.find(s => s.id == servicioId);
  }

  guardarTarifa(){
    if(this.formTarifa.valid){
      const nuevaTarifa = {
        tipoServicio: this.servicioSeleccionado,
        precio: this.formTarifa.value.precio
      };
      this.tarifaService.crearTarifa(this.idCementerio, nuevaTarifa).subscribe({
        next: (response) => {
          console.log(response);
          this.modalError.abrirModal("Exito", "Tarifa creada correctamente", false);
          this.formTarifa.reset();
          this.servicioSeleccionado = null;
          this.cargarDatos();
        },
        error: (err) => {
          console.error(err);
          this.modalError.abrirModal("Error", err.error, true);
        }
      });
    }else{
      this.formTarifa.markAllAsTouched();
    }
  }

  borrarTarifa(idTarifa: number){
    this.idTarifaABorrar = idTarifa;
    this.modalConfirmar.abrir();
  }

  confirmarBorrado(){
    if(this.idTarifaABorrar !== null){
      this.tarifaService.borrarTarifa(this.idTarifaABorrar).subscribe({
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

  actualizarPrecio(tarifa: any, nuevoPrecio: string){
    const precioNum = parseFloat(nuevoPrecio);
    if(!isNaN(precioNum) && precioNum >= 0){
      tarifa.precio = precioNum;
      tarifa.modificado = true;
    }
  }

  guardarCambioTarifa(tarifa: any){
    const tarifaActualizada = {
      id: tarifa.id,
      tipoServicio: tarifa.tipoServicio,
      precio: tarifa.precio
    };
    this.tarifaService.actualizarTarifa(tarifa.id, tarifaActualizada).subscribe({
      next: (response) => {
        console.log(response);
        this.modalError.abrirModal("Exito", "Tarifa actualizada correctamente", false);
        tarifa.modificado = false;
        this.cdr.markForCheck();
      },
      error: (err) => {
        console.error(err);
        this.modalError.abrirModal("Error", err.error, true);
      }
    });
  }

}
