import { Component, ViewChild } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ModalError } from "../modal-error/modal-error";
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Validaciones } from '../../validators/validaciones';
import { Validators } from '@angular/forms';
import { AuthService } from '../../services/auth-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cambiar-contrasena',
  imports: [ReactiveFormsModule, ModalError, CommonModule, RouterLink],
  templateUrl: './cambiar-contrasena.html',
  styleUrl: './cambiar-contrasena.css',
})
export class CambiarContrasena {

  contrasenas!: FormGroup;
  @ViewChild("modalError") modalError!: ModalError;
  verContrasena: boolean = false;
  id: number = 0;

  constructor(private route: ActivatedRoute, private auth: AuthService) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.id = params['id'];
    });

    this.contrasenas = new FormGroup({
      contrasenaActual: new FormControl('', [Validators.required, Validators.minLength(8)]),
      contrasena: new FormControl('', [Validators.required, Validators.minLength(8)]),
      repetirContrasena: new FormControl('', [Validators.required])
    }, {
      validators: Validaciones.contrasenaIgual
    });

  }

  cambiar(){
    if (this.contrasenas.valid) {
      const contrasenas = this.contrasenas.value;
      delete contrasenas.repetirContrasena;

      this.auth.cambiarContrasena(this.id, contrasenas).subscribe({
        next: (response) => {
          console.log(response);
          this.modalError.abrirModal("Exito", response, false);
          this.contrasenas.reset();
        },
        error: (err) => {
          this.modalError.abrirModal("Error", err.error, true);
          console.error(err);
        }
      });
    }else{
      this.contrasenas.markAllAsTouched();
    }
  }

  toggleContrasena() {
    this.verContrasena = !this.verContrasena;
  }

}
