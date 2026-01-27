import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { UserLogin } from './userLogin';
import { Validaciones } from '../../validators/validaciones';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { LoginService } from '../../services/login';
import { ModalError } from '../modal-error/modal-error';
import { ViewChild } from '@angular/core';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, CommonModule, RouterLink, ModalError],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login implements OnInit{

  usuario!: UserLogin;
  loginForm!: FormGroup;
  @ViewChild("modalError") modalError!: ModalError;
  verContrasena: boolean = false;

  constructor(private login: LoginService, private router: Router) {}

  ngOnInit(): void {
    this.usuario = new UserLogin();
    this.loginForm = new FormGroup({
      email: new FormControl('' , [Validators.required, Validators.email]),
      contrasena: new FormControl('' , [Validators.required, Validators.minLength(8)])
    });
  }

  entrar() {
    if (this.loginForm.valid) {
      this.usuario = this.loginForm.value;
      
      this.login.getTokenFromServer(this.usuario).subscribe({
        next: (respuesta) => {
          const token = respuesta.token;
          console.log(token);
          this.login.getUsuarioFromServer(token).subscribe({
            next: (usuario) => {
              console.log(usuario);
              this.login.login(usuario, token);
              this.router.navigate(['/home']);
            },
            error: (err) => {
              console.error(err);
            }
          });
        },
        error: (err) => {
          this.modalError.abrirModal("Error", err.error, true);
          console.error(err);
        }
      });
    }
  }

  toggleContrasena() {
    this.verContrasena = !this.verContrasena;
  }

}
