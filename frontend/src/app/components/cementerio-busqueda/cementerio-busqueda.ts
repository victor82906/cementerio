import { Component } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CementerioService } from '../../services/cementerio-service';
import { OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';
import { AuthService } from '../../services/auth-service';
import { Footer } from "../footer/footer";
import { Cabecera } from "../cabecera/cabecera";

@Component({
  selector: 'app-cementerio-busqueda',
  imports: [FormsModule, CommonModule, RouterLink, Footer, Cabecera],
  templateUrl: './cementerio-busqueda.html',
  styleUrl: './cementerio-busqueda.css',
})
export class CementerioBusqueda implements OnInit{

  busqueda: string = '';
  cementerios: any[] = [
  {
    "id": 1,
    "nombre": "Cementerio Municipal de San José",
    "direccion": "Calle del Reposo, 12, Granada",
    "foto": "https://images.unsplash.com/photo-1551009175-8a68da93d5f9?w=800",
    "ayuntamiento": {
      "id": 101,
      "nombre": "Excelentísimo Ayuntamiento de Granada",
      "email": "contacto@granada.es",
      "telefono": "958248100",
      "direccion": "Plaza del Carmen, s/n",
      "codigo": "AYTO-GR-01"
    }
  },
  {
    "id": 2,
    "nombre": "Cementerio Nuestra Señora de la Almudena",
    "direccion": "Avenida de Daroca, 90, Madrid",
    "foto": "https://images.unsplash.com/photo-1606760227091-3dd870d97f1d?w=800",
    "ayuntamiento": {
      "id": 102,
      "nombre": "Ayuntamiento de Madrid",
      "email": "info@madrid.es",
      "telefono": "915298210",
      "direccion": "Palacio de Cibeles",
      "codigo": "AYTO-MD-05"
    }
  },
  {
    "id": 3,
    "nombre": "Cementerio de San Isidro",
    "direccion": "Paseo de la Ermita del Santo, 72, Madrid",
    "foto": "https://images.unsplash.com/photo-1595113316349-9fa4ee24f884?w=800",
    "ayuntamiento": {
      "id": 102,
      "nombre": "Ayuntamiento de Madrid",
      "email": "info@madrid.es",
      "telefono": "915298210",
      "direccion": "Palacio de Cibeles",
      "codigo": "AYTO-MD-05"
    }
  }
];

  constructor(private auth: AuthService, private route: ActivatedRoute, private cementerioService: CementerioService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
      this.route.queryParams.subscribe(params => {
        this.busqueda = params['nombre'];
        this.buscar();
      });
  }

  buscar(){
    if(this.busqueda.trim()){
      this.cementerioService.buscaNombre(this.busqueda).subscribe({
        next: (response) => {
          this.cementerios = response;
          this.cdr.markForCheck();
        },
        error: (err) => {
          console.error(err);
        }
      });
    }else{
      this.cementerioService.getAll().subscribe({
        next: (response) => {
          this.cementerios = response;
          this.cdr.markForCheck();
        },
        error: (err) => {
          console.error(err);
        }
      });
    }
  }

}
