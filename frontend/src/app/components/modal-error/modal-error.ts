import { Component,ChangeDetectorRef } from '@angular/core';

declare var bootstrap: any;

@Component({
  selector: 'app-modal-error',
  imports: [],
  templateUrl: './modal-error.html',
  styleUrl: './modal-error.css',
})
export class ModalError {

  titulo: string = "";
  mensajes: any;

  constructor(private cdr: ChangeDetectorRef) {}

  public abrirModal(titulo: string, contenido: any, error: boolean){
    this.titulo = titulo;

    if (contenido && typeof contenido === 'object' && !Array.isArray(contenido)) {
      // Si es un objeto (como el de tu imagen), extraemos solo los mensajes (valores)
      this.mensajes = Object.values(contenido);
    } else if (Array.isArray(contenido)) {
      // Si ya es un array, lo dejamos tal cual
      this.mensajes = contenido;
    } else {
      // Si es un string suelto, lo metemos en un array
      this.mensajes = [contenido];
    }

    this.cdr.detectChanges();

    const modalError = document.getElementById("modalError");
    const aviso = document.getElementById("aviso");
    if (error) {
      aviso?.classList.add("bg-danger");
      aviso?.classList.remove("bg-success");
    }else{
      aviso?.classList.add("bg-success");
      aviso?.classList.remove("bg-danger");
    }
    const modal = new bootstrap.Modal(modalError);
    modal.show();
  }

}
