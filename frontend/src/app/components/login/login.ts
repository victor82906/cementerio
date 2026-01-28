import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { UserLogin } from './userLogin';
import { Validaciones } from '../../validators/validaciones';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { LoginService } from '../../services/login';
import { ModalError } from '../modal-error/modal-error';
import { ViewChild } from '@angular/core';
import { AuthService } from '../../services/auth-service';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, CommonModule, RouterLink, ModalError],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login implements OnInit{

  loginForm!: FormGroup;
  @ViewChild("modalError") modalError!: ModalError;
  verContrasena: boolean = false;

  constructor(private login: LoginService, private router: Router, private auth: AuthService) {}

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      email: new FormControl('' , [Validators.required, Validators.email]),
      contrasena: new FormControl('' , [Validators.required, Validators.minLength(8)])
    });
  }

  entrar() {
    if (this.loginForm.valid) {
      this.login.logout();
      this.login.getTokenFromServer(this.loginForm.value).subscribe({
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
    }
  }

  guardarUsuario(){
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
