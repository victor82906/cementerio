import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CiudadanoService {

  private apiUrl = "http://localhost:8081/ciudadano";

  constructor(private http: HttpClient){}

  registrarCiudadano(ciudadano: any): Observable<any> {
    return this.http.post(this.apiUrl, ciudadano);
  }

  findById(id: number): Observable<any> {
    return this.http.get(this.apiUrl + "/" + id);
  }

  update(id: number, ciudadano: any): Observable<any> {
    return this.http.put(this.apiUrl + "/" + id, ciudadano);
  }

}
