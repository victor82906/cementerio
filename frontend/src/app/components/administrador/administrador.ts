import { Component } from '@angular/core';
import { AuthService } from '../../services/auth-service';

@Component({
  selector: 'app-administrador',
  imports: [],
  templateUrl: './administrador.html',
  styleUrl: './administrador.css',
})
export class Administrador {

  constructor(private auth: AuthService){}



}
