import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AyuntamientoService {
  
  private apiUrl = "http://localhost:8081/ayuntamiento";

  constructor(private http: HttpClient){}

  registrarAyuntamiento(ayuntamiento: any): Observable<any> {
    return this.http.post(this.apiUrl, ayuntamiento);
  }

  findById(id: number): Observable<any> {
    return this.http.get(this.apiUrl + "/" + id);
  }
  
  update(id: number, ayuntamiento: any): Observable<any> {
    return this.http.put(this.apiUrl + "/" + id, ayuntamiento);
  }

  getAll(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  buscaNombreEmail(busqueda: string): Observable<any> {
    return this.http.get(this.apiUrl + '?nombre=' + busqueda);
  }

  borrar(id: number): Observable<any> {
    return this.http.delete(this.apiUrl + "/" + id);
  }

}
