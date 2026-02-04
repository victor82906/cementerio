import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { CementerioService } from '../../services/cementerio-service';
import { ModalError } from "../modal-error/modal-error";
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';


@Component({
  selector: 'app-registro-cementerio',
  imports: [ReactiveFormsModule, CommonModule, RouterLink, ModalError],
  templateUrl: './registro-cementerio.html',
  styleUrl: './registro-cementerio.css',
})
export class RegistroCementerio {

  cementerioForm!: FormGroup;
  @ViewChild("modalError") modalError!: ModalError;
  idAyuntamiento: number = 0;

  imgPreview: string | null = null;
  fotoSeleccionada: File | null = null;

  constructor(private cdr: ChangeDetectorRef, private cementerioService: CementerioService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.idAyuntamiento = params['idAyuntamiento'];
    });

    this.cementerioForm = new FormGroup({
      nombre: new FormControl('', [Validators.required]),
      direccion: new FormControl('', [Validators.required, Validators.maxLength(100)]),
      tiempoExhumacion: new FormControl('', [Validators.required, Validators.min(1)])
    });
  }

  registrar() {
    if (this.cementerioForm.valid) {
      if (!this.fotoSeleccionada) {
        this.modalError.abrirModal("Error", "Debe seleccionar una foto para el cementerio", true);
      } else {
        const formData = new FormData();
        formData.append('foto', this.fotoSeleccionada);
        formData.append('datos', new Blob([JSON.stringify(this.cementerioForm.value)], { type: 'application/json' }));
        const blob = formData.get('datos') as Blob;
        blob.text().then(text => {
          console.log("JSON que se envía al servidor:", JSON.parse(text));
        });

        this.cementerioService.registrarCementerio(formData, this.idAyuntamiento).subscribe({
          next: (response) => {
            console.log(response);
            this.modalError.abrirModal("Exito", "El cementerio se ha registrado correctamente", false);
            this.cementerioForm.reset();
            this.imgPreview = null;
            this.fotoSeleccionada = null;
            const inputFoto = document.getElementById("foto") as HTMLInputElement;
            inputFoto.value = "";
            this.cdr.detectChanges();
          },
          error: (err) => {
            this.modalError.abrirModal("Error", err.error, true);
            console.error(err);
          }
        });
      }
    } else {
      this.cementerioForm.markAllAsTouched();
    }
  }

  previsualizarFoto(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files[0]) {
      this.fotoSeleccionada = input.files[0];

      // Validar tamaño (2MB)
      if (this.fotoSeleccionada.size > 2 * 1024 * 1024) {
        this.modalError.abrirModal("Error", "La imagen no puede superar los 2MB", true);
        input.value = '';
        this.imgPreview = null;
        return;
      }

      // Crear URL para la vista previa
      const reader = new FileReader();
      reader.onload = () => {
        this.imgPreview = reader.result as string;
        this.cdr.detectChanges();
      };
      reader.readAsDataURL(this.fotoSeleccionada);
    }
  }

}
