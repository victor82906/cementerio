import { Component } from '@angular/core';
import { Footer } from "../footer/footer";
import { Cabecera } from '../cabecera/cabecera';
import { AuthService } from '../../services/auth-service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CiudadanoService } from '../../services/ciudadano-service';
import { ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ModalConfirmar } from "../modal-confirmar/modal-confirmar";
import { ViewChild } from '@angular/core';
import { Cargando } from '../cargando/cargando';

@Component({
  selector: 'app-busqueda-ciudadanos',
  imports: [Footer, Cabecera, FormsModule, CommonModule, RouterLink, ModalConfirmar, Cargando],
  templateUrl: './busqueda-ciudadanos.html',
  styleUrl: './busqueda-ciudadanos.css',
})
export class BusquedaCiudadanos {

  busqueda: string = '';
  ciudadanos: any[] = [];
  @ViewChild("modalConfirmar") modalConfirmar!: ModalConfirmar;
  idCiudadanoABorrar: number | null = null;
  cargando: boolean = false;

  constructor(private auth: AuthService, private router: Router, private ciudadanoService: CiudadanoService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.buscar();
  }
  
  buscar(){
    this.cargando = true;
    if(this.busqueda.trim()){
      this.ciudadanoService.buscaNombreApellidosEmail(this.busqueda).subscribe({
        next: (response) => {
          this.ciudadanos = response;
          this.cargando = false;
          this.cdr.markForCheck();
        },
        error: (err) => {
          console.error(err);
        }
      });
    }else{
      this.ciudadanoService.getAll().subscribe({
        next: (response) => {
          this.ciudadanos = response;
          this.cargando = false;
          this.cdr.markForCheck();
        },
        error: (err) => {
          console.error(err);
        }
      });
    }
  }

  editarCiudadano(id: number){
    this.router.navigate(['/editar-ciudadano'], { queryParams: { id: id } });
  }

  borrarCiudadano(id: number){
    this.idCiudadanoABorrar = id;
    this.modalConfirmar.abrir();
  }

  confirmarBorrado(){
    if(this.idCiudadanoABorrar !== null){
      this.ciudadanoService.delete(this.idCiudadanoABorrar).subscribe({
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

  verDifuntos(id: number){
    this.router.navigate(['/difuntos-ciudadano'], { queryParams: { idCiudadano: id } });
  }


}
