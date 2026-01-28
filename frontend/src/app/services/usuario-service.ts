import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  
  private apiUrl = "http://localhost:8081/usuario";

  constructor(private http: HttpClient){}

  subirFoto(foto: any, id: number): Observable<any>{
    return this.http.post(this.apiUrl + "/foto/" + id, foto);
  }

}
