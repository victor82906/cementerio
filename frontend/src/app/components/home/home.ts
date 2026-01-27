import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  
  busqueda: string = '';

  constructor(private router: Router) {}

  buscar(){
    if(this.busqueda.trim()){
      this.router.navigate(['/busqueda-cementerio'], { queryParams: { nombre: this.busqueda } });
    }
  }
}
