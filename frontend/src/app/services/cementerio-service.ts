import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CementerioService {
  private apiUrl = 'http://localhost:8081/cementerio';

  constructor(private http: HttpClient){}

  getAll(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  buscaNombre(nombre: String): Observable<any> {
    return this.http.get(this.apiUrl + '?nombre=' + nombre);
  }
}
