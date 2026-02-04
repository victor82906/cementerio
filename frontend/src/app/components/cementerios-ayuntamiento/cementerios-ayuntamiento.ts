import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { Cabecera } from '../cabecera/cabecera';
import { Footer } from '../footer/footer';
import { CementerioService } from '../../services/cementerio-service';
import { ChangeDetectorRef } from '@angular/core';
import { Cargando } from '../cargando/cargando';

@Component({
  selector: 'app-cementerios-ayuntamiento',
  imports: [Cabecera, Footer, Cargando],
  templateUrl: './cementerios-ayuntamiento.html',
  styleUrl: './cementerios-ayuntamiento.css',
})
export class CementeriosAyuntamiento {

  idAyuntamiento: number = 0;
  cementerios: any[] = [];
  cargando: boolean = true;

  constructor(private cdr: ChangeDetectorRef, private route: ActivatedRoute, private router: Router, private cementerioService: CementerioService) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.idAyuntamiento = params['idAyuntamiento'];
    });

    this.cargarCementerios();
  }

  cargarCementerios(){
    this.cementerioService.getByAyuntamiento(this.idAyuntamiento).subscribe({
      next: (response) => {
        this.cementerios = response;
        this.cdr.markForCheck();
        this.cargando = false;
      },
      error: (err) => {
        console.error(err);
        this.cargando = false;
      }
    });
  }

  nuevoCementerio(){
    this.router.navigate(['/registro-cementerio'], { queryParams: { idAyuntamiento: this.idAyuntamiento } });
  }

  abrirCementerio(id: number){
    this.router.navigate(['/cementerio'], { queryParams: { id: id } });
  }

}
