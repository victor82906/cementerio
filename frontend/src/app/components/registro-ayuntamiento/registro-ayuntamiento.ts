import { Component, OnInit, ViewChild } from '@angular/core';
import { Ayuntamiento } from './ayuntamiento';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AyuntamientoService } from '../../services/ayuntamiento-service';
import { Router, RouterLink } from '@angular/router';
import { Validaciones } from '../../validators/validaciones';
import { ModalError } from "../modal-error/modal-error";

@Component({
  selector: 'app-registro-ayuntamiento',
  imports: [ReactiveFormsModule, CommonModule, RouterLink, ModalError],
  templateUrl: './registro-ayuntamiento.html',
  styleUrl: './registro-ayuntamiento.css',
})
export class RegistroAyuntamiento implements OnInit{

  // ayuntamiento!: Ayuntamiento;
  registroAyto!: FormGroup;
  @ViewChild(ModalError) modalError!: ModalError;
  verContrasena: boolean = false;

  constructor(private ayuntamientoService: AyuntamientoService, private router: Router) {}

  ngOnInit(): void {
    // this.ayuntamiento = new Ayuntamiento();

    this.registroAyto = new FormGroup({
      nombre: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$')]),
      email: new FormControl('', [Validators.required, Validators.email]),
      telefono: new FormControl('', [Validators.required, Validators.pattern('^[679][0-9]{8}$')]),
      direccion: new FormControl('', [Validators.maxLength(50)]),
      codigo: new FormControl('', [Validators.required]),
      contrasena: new FormControl('', [Validators.required, Validators.minLength(8)]),
      repetirContrasena: new FormControl('', [Validators.required])
    }, {
      validators: Validaciones.contrasenaIgual
    });
  }

  registrar(){
    if (this.registroAyto.valid) {
      const ayuntamiento = this.registroAyto.value;
      delete ayuntamiento.repetirContrasena;

      // this.ayuntamiento = this.registroAyto.value;

      this.ayuntamientoService.registrarAyuntamiento(ayuntamiento).subscribe({
        next: (response) => {
          console.log(response);
          this.modalError.abrirModal("Exito", "El ayuntamiento se ha registrado correctamente", false);
          this.registroAyto.reset();
        },
        error: (err) => {
          this.modalError.abrirModal("Error", err.error, true);
          console.error(err);
        }
      });
    }else{
      this.registroAyto.markAllAsTouched();
    }

  }

  toggleContrasena() {
    this.verContrasena = !this.verContrasena;
  }  

}