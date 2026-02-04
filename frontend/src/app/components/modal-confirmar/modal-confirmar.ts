import { Component, EventEmitter, Output } from '@angular/core';
declare var bootstrap: any;

@Component({
  selector: 'app-modal-confirmar',
  imports: [],
  templateUrl: './modal-confirmar.html',
  styleUrl: './modal-confirmar.css',
})

export class ModalConfirmar {
  @Output() accionConfirmada = new EventEmitter<void>();
  private modalInstance: any;

  abrir() {
    const element = document.getElementById('modalConfirmar');
    this.modalInstance = new bootstrap.Modal(element);
    this.modalInstance.show();
  }

  confirmar() {
    this.accionConfirmada.emit();
    this.modalInstance.hide();
  }
}