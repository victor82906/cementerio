import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CementerioService {
  private apiUrl = 'http://localhost:8081/cementerio';
  private apiAyuntamiento = 'http://localhost:8081/ayuntamiento';


  constructor(private http: HttpClient){}

  getAll(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  buscaNombre(nombre: String): Observable<any> {
    return this.http.get(this.apiUrl + '?nombre=' + nombre);
  }

  getByAyuntamiento(idAyuntamiento: number): Observable<any> {
    return this.http.get(this.apiAyuntamiento + '/' + idAyuntamiento + '/cementerio');
  }

  registrarCementerio(cementerioData: any, idAyuntamiento: number): Observable<any> {
    return this.http.post(this.apiAyuntamiento + '/' + idAyuntamiento + '/cementerio', cementerioData);
  }

  findById(id: number): Observable<any> {
    return this.http.get(this.apiUrl + "/" + id);
  }

}
