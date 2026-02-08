import { Component, ViewChild } from '@angular/core';
import { FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { ModalError } from "../modal-error/modal-error";
import { RouterLink } from '@angular/router';
import { ServicioService } from '../../services/servicio-service';
import { Cabecera } from '../cabecera/cabecera';
import { Footer } from '../footer/footer';
import { AuthService } from '../../services/auth-service';
import { ChangeDetectorRef } from '@angular/core';
import { Cargando } from '../cargando/cargando';
import { ModalConfirmar } from '../modal-confirmar/modal-confirmar';

@Component({
  selector: 'app-crear-tipo-servicio',
  imports: [ReactiveFormsModule, ModalError, Cabecera, Footer, Cargando, ModalConfirmar],
  templateUrl: './crear-tipo-servicio.html',
  styleUrl: './crear-tipo-servicio.css',
})
export class CrearTipoServicio {

  tipoServicioForm!: FormGroup;
  tiposServicios: any[] = [];
  cargando: boolean = false;
  idServicioABorrar: number | null = null;
  @ViewChild("modalError") modalError!: ModalError;
  @ViewChild('modalConfirmar') modalConfirmar!: ModalConfirmar;

  constructor(private servicioService: ServicioService, private auth: AuthService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.tipoServicioForm = new FormGroup({
      nombre: new FormControl('', [Validators.required]),
      descripcion: new FormControl('', [Validators.required, Validators.maxLength(200)])
    });
    this.cargaServicios();
  }

  cargaServicios(){
    this.cargando = true;
    this.servicioService.getAllTipoServicios().subscribe({
      next: (response) => {
        this.tiposServicios = response;
        this.cargando = false;
        this.cdr.markForCheck();
      },
      error: (err) => {
        console.error(err);
        this.cargando = false;
        this.cdr.markForCheck();
      }
    });
  }

  crear(){
    if (this.tipoServicioForm.valid) {
      this.servicioService.crearTipoServicio(this.tipoServicioForm.value).subscribe({
        next: (response) => {
          console.log(response);
          this.modalError.abrirModal("Exito", "Creado. ¡¡Ahora añade tarifa a este servicio desde tu cementerio!!", false);
          this.tipoServicioForm.reset();
        },
        error: (err) => {
          this.modalError.abrirModal("Error", err.error, true);
          console.error(err);
        }
      });
    }else{
      this.tipoServicioForm.markAllAsTouched();
    }
  }

  esAdmin(){
    return this.auth.getRol() == "ADMIN";
  }

  borraServicio(id: number){
    this.idServicioABorrar = id;
    this.modalConfirmar.abrir();
  }

  confirmarBorrado(){
    if(this.idServicioABorrar !== null){
      this.servicioService.borrarTipoServicio(this.idServicioABorrar).subscribe({
        next: (response) => {
          console.log(response);
          this.cargaServicios();
        },
        error: (err) => {
          console.error(err);
          this.modalError.abrirModal("Error", err.error, true);
        }
      });
    }
  }

}
