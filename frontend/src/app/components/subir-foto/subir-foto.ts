import { Component } from '@angular/core';
import { ModalError } from "../modal-error/modal-error";
import { ChangeDetectorRef } from '@angular/core';
import { RouterLink } from '@angular/router';
import { UsuarioService } from '../../services/usuario-service';
import { AuthService } from '../../services/auth-service';
import { ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-subir-foto',
  imports: [ModalError, RouterLink],
  templateUrl: './subir-foto.html',
  styleUrl: './subir-foto.css',
})

export class SubirFotoComponent {
  imgSeleccionada: string | ArrayBuffer | null = null;
  archivoCapturado: File | null = null;
  @ViewChild("modalError") modalError!: ModalError;
  id: number = 0;

  constructor(private service: UsuarioService, private cdr: ChangeDetectorRef, private auth: AuthService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.id = params['id'];
    });
  }

  previsualizarFoto(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.archivoCapturado = file;
      const reader = new FileReader();
      
      // Se ejecuta cuando el archivo se ha leído
      reader.onload = (e) => {
        this.imgSeleccionada = reader.result;
        this.cdr.detectChanges();
      }
      // Lee la imagen como una URL de datos (Base64)
      reader.readAsDataURL(file);
    }
  }

  enviarAlServidor() {
    if (this.archivoCapturado) {
      const formData = new FormData();
      formData.append('foto', this.archivoCapturado);
      // Tu llamada al servicio
      this.service.subirFoto(formData, this.id).subscribe({
        next: (resp) => {
          console.log(resp);
          this.modalError.abrirModal("Exito", "La foto se ha subido correctamente", false);
          this.imgSeleccionada = null;
          this.archivoCapturado = null;
          this.cdr.detectChanges();
          const inputFoto = document.getElementById("foto") as HTMLInputElement;
          inputFoto.value = "";
        },
        error: (err) => {
          console.error(err)
          this.modalError.abrirModal("Error", err.error, true);
        }
      });
    }
  }
}