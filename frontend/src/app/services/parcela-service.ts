import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ParcelaService {
  
  private apiUrl = 'http://localhost:8081/parcela';
  private apiZona = 'http://localhost:8081/zona';

  constructor(private http: HttpClient) {}

  getParcela(id: number): Observable<any> {
    return this.http.get(this.apiUrl + '/' + id);
  }

  getParcelasZona(idZona: number): Observable<any> {
    return this.http.get(this.apiZona + '/' + idZona + '/parcela');
  }

  getAyuntamiento(idParcela: number): Observable<any> {
    return this.http.get(this.apiUrl + '/' + idParcela + '/ayuntamiento');
  }

  getZona(idParcela: number): Observable<any> {
    return this.http.get(this.apiUrl + '/' + idParcela + '/zona');
  }

}
