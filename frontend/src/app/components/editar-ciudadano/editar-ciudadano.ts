import { Component } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CiudadanoService } from '../../services/ciudadano-service';
import { AuthService } from '../../services/auth-service';
import { CommonModule } from '@angular/common';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Validaciones } from '../../validators/validaciones';
import { ModalError } from "../modal-error/modal-error";
import { ViewChild } from '@angular/core';

@Component({
  selector: 'app-editar-ciudadano',
  imports: [ReactiveFormsModule, CommonModule, RouterLink, ModalError],
  templateUrl: './editar-ciudadano.html',
  styleUrl: './editar-ciudadano.css',
})
export class EditarCiudadano {

  editarForm!: FormGroup;
  id: number = 0;
  @ViewChild("modalError") modalError!: ModalError;
  fotoPerfil: string = "";

  constructor(private ciudadanoService: CiudadanoService, private authService: AuthService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.id = params['id'];
    });

    this.cargarCiudadano();

    this.editarForm = new FormGroup({
      nombre: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$')]),
      apellidos: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$')]),
      email: new FormControl('', [Validators.required, Validators.email]),
      telefono: new FormControl('', [Validators.required, Validators.pattern('^[679][0-9]{8}$')]),
      direccion: new FormControl('', [Validators.maxLength(50)]),
      dni: new FormControl('', [Validators.required, Validaciones.dniValido])
    });
  }

  cargarCiudadano(){
    this.ciudadanoService.findById(this.id).subscribe({
      next: (response) => {
        this.editarForm.patchValue(response);
        this.fotoPerfil = response.foto;
        console.log(response);
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  actualizar(){
    if (this.editarForm.valid) {
      this.ciudadanoService.update(this.id, this.editarForm.value).subscribe({
        next: (response) => {
          console.log(response);
          this.modalError.abrirModal("Exito", "Usuario editado correctamente", false);
        },
        error: (err) => {
          this.modalError.abrirModal("Error", err.error, false);
          console.error(err);
        }
      });
    }
  }

  foto(){
    this.router.navigate(['/subir-foto'], {queryParams: {id: this.id}})
  }

  contrasena(){
    this.router.navigate(['/cambiar-contrasena'], {queryParams: {id: this.id}})
  }

}
