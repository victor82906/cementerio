import { Component, ViewChild } from '@angular/core';
import { FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { ModalError } from "../modal-error/modal-error";
import { RouterLink } from '@angular/router';
import { ServicioService } from '../../services/servicio-service';

@Component({
  selector: 'app-crear-tipo-servicio',
  imports: [ReactiveFormsModule, ModalError, RouterLink],
  templateUrl: './crear-tipo-servicio.html',
  styleUrl: './crear-tipo-servicio.css',
})
export class CrearTipoServicio {

  tipoServicioForm!: FormGroup;
  @ViewChild("modalError") modalError!: ModalError;

  constructor(private servicioService: ServicioService) {}

  ngOnInit(): void {
    this.tipoServicioForm = new FormGroup({
      nombre: new FormControl('', [Validators.required]),
      descripcion: new FormControl('', [Validators.required, Validators.maxLength(200)])
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


}
