import { Component, ViewChild } from '@angular/core';
import { ModalConfirmar } from '../modal-confirmar/modal-confirmar';
import { AyuntamientoService } from '../../services/ayuntamiento-service';
import { AuthService } from '../../services/auth-service';
import { Router, RouterLink } from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';
import { Footer } from '../footer/footer';
import { Cabecera } from '../cabecera/cabecera';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Cargando } from '../cargando/cargando';


@Component({
  selector: 'app-busqueda-ayuntamientos',
  imports: [Footer, Cabecera, ModalConfirmar, FormsModule, CommonModule, RouterLink, Cargando],
  templateUrl: './busqueda-ayuntamientos.html',
  styleUrl: './busqueda-ayuntamientos.css',
})
export class BusquedaAyuntamientos {

  busqueda: string = '';
  ayuntamientos: any[] = [];
  @ViewChild('modalConfirmar') modalConfirmar!: ModalConfirmar;
  idAyuntamientoABorrar: number | null = null;
  cargando: boolean = false;

  constructor(private auth: AuthService, private router: Router, private ayuntamientoService: AyuntamientoService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.buscar();
  }

  buscar(){
    this.cargando = true;
    if(this.busqueda.trim()){
      this.ayuntamientoService.buscaNombreEmail(this.busqueda).subscribe({
        next: (response) => {
          this.ayuntamientos = response;
          this.cargando = false;
          this.cdr.markForCheck();
        },
        error: (err) => {
          console.error(err);
        }
      });
    }else{
      this.ayuntamientoService.getAll().subscribe({
        next: (response) => {
          this.ayuntamientos = response;
          this.cargando = false;
          this.cdr.markForCheck();
        },
        error: (err) => {
          console.error(err);
        }
      });
    }
  }

  editarAyuntamiento(id: number){
    this.router.navigate(['/editar-ayuntamiento'], { queryParams: { id: id } });
  }

  borrarAyuntamiento(id: number){
    this.idAyuntamientoABorrar = id;
    this.modalConfirmar.abrir();
  }

  confirmarBorrado(){
    if(this.idAyuntamientoABorrar !== null){
      this.ayuntamientoService.borrar(this.idAyuntamientoABorrar).subscribe({
        next: (response) => {
          console.log(response);
          this.buscar();
        },
        error: (err) => {
          console.error(err);
        }
      });
    }
  }

  verCementerios(id: number){
    this.router.navigate(['/cementerios-ayuntamiento'], { queryParams: { idAyuntamiento: id } });
  }

}
