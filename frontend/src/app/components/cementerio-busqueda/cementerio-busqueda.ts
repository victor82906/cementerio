import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CementerioService } from '../../services/cementerio-service';
import { OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';
import { AuthService } from '../../services/auth-service';
import { Footer } from "../footer/footer";
import { Cabecera } from "../cabecera/cabecera";
import { Cargando } from '../cargando/cargando';

@Component({
  selector: 'app-cementerio-busqueda',
  imports: [FormsModule, CommonModule, Footer, Cabecera, Cargando],
  templateUrl: './cementerio-busqueda.html',
  styleUrl: './cementerio-busqueda.css',
})
export class CementerioBusqueda implements OnInit{

  busqueda: string = '';
  cementerios: any[] = [];
  cargando: boolean = false;

  constructor(private router: Router, private auth: AuthService, private route: ActivatedRoute, private cementerioService: CementerioService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
      this.route.queryParams.subscribe(params => {
        this.busqueda = params['nombre'] || '';
      });
      this.buscar();
  }

  buscar(){
    this.cargando = true;
    if(this.busqueda.trim()){
      this.cementerioService.buscaNombre(this.busqueda).subscribe({
        next: (response) => {
          this.cementerios = response;
          this.cargando = false;
          this.cdr.markForCheck();
        },
        error: (err) => {
          console.error(err);
          this.cargando = false;
        }
      });
    }else{
      this.cementerioService.getAll().subscribe({
        next: (response) => {
          this.cementerios = response;
          this.cargando = false;
          this.cdr.markForCheck();
        },
        error: (err) => {
          console.error(err);
          this.cargando = false;
        }
      });
    }
  }

  verCementerio(idCementerio: number){
    this.router.navigate(['/cementerio'], { queryParams: { id: idCementerio } });
  }

}
