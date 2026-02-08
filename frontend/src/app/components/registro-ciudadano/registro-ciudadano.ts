import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { Validaciones } from '../../validators/validaciones';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { CiudadanoService } from '../../services/ciudadano-service';
import { ModalError } from "../modal-error/modal-error";
import { ViewChild } from '@angular/core';
import { AuthService } from '../../services/auth-service';
import { LoginService } from '../../services/login';

@Component({
  selector: 'app-registro-ciudadano',
  imports: [ReactiveFormsModule, CommonModule, RouterLink, ModalError],
  templateUrl: './registro-ciudadano.html',
  styleUrl: './registro-ciudadano.css',
})
export class RegistroCiudadano implements OnInit {
  registroCiudadano!: FormGroup;
  @ViewChild("modalError") modalError!: ModalError;
  verContrasena: boolean = false;

  constructor(private ciudadanoService: CiudadanoService, private router: Router, private auth: AuthService, private login: LoginService) { }

  ngOnInit(): void {
    this.registroCiudadano = new FormGroup({
      nombre: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$')]),
      apellidos: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$')]),
      email: new FormControl('', [Validators.required, Validators.email]),
      telefono: new FormControl('', [Validators.required, Validators.pattern('^[679][0-9]{8}$')]),
      direccion: new FormControl('', [Validators.maxLength(50)]),
      dni: new FormControl('', [Validators.required, Validaciones.dniValido]),
      contrasena: new FormControl('', [Validators.required, Validators.minLength(8)]),
      repetirContrasena: new FormControl('', [Validators.required])
    }, {
      validators: Validaciones.contrasenaIgual
    });
  }

  registrar() {
    if (this.registroCiudadano.valid) {
      const ciudadano = this.registroCiudadano.value;
      ciudadano.dni = ciudadano.dni.toUpperCase();
      delete ciudadano.repetirContrasena;

      this.ciudadanoService.registrarCiudadano(ciudadano).subscribe({
        next: (response) => {
          console.log(response);
            if (this.auth.getRol() !== 'ADMIN' && this.auth.getRol() !== 'AYUNTAMIENTO') {
            const loginForm = {
              email: ciudadano.email,
              contrasena: ciudadano.contrasena
            };
            this.login.getTokenFromServer(loginForm).subscribe({
              next: (respuesta) => {
                const token = respuesta.token;
                this.login.setToken(token);
                this.guardarUsuario();
              },
              error: (err) => {
                this.modalError.abrirModal("Error", err.error, true);
                console.error(err);
              }
            });
          }else {
            this.modalError.abrirModal("Exito", "El ciudadano se ha registrado correctamente", false);
          }
          this.registroCiudadano.reset();
        },
        error: (err) => {
          this.modalError.abrirModal("Error", err.error, true);
          console.error(err);
        }
      });
    } else {
      this.registroCiudadano.markAllAsTouched();
    }
  }

  guardarUsuario() {
    this.login.getUsuarioFromServer(this.auth.getToken() || '').subscribe({
      next: (respuesta) => {
        this.login.setUsuario(respuesta);
        this.router.navigate(['/home']);
      },
      error: (err) => {
        this.modalError.abrirModal("Error", err.error, true);
        console.error(err);
      }
    });
  }

  toggleContrasena() {
    this.verContrasena = !this.verContrasena;
  }

}
