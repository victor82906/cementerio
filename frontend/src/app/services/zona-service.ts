import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class ZonaService {

  private apiUrl = 'http://localhost:8081/zona';
  private apiCementerio = 'http://localhost:8081/cementerio';
  private apiTipoZona = 'http://localhost:8081/tipo-zona';

  constructor(private http: HttpClient) {}

  getZona(id: number): Observable<any> {
    return this.http.get(this.apiUrl + "/" + id);
  }

  getTiposZona(): Observable<any> {
    return this.http.get(this.apiTipoZona);
  }

  crearZona(idCementerio: number, zonaData: any): Observable<any> {
    return this.http.post(this.apiCementerio + "/" + idCementerio + "/zona", zonaData);
  }

  getZonasCementerio(idCementerio: number): Observable<any> {
    return this.http.get(this.apiCementerio + "/" + idCementerio + "/zona");
  }

  borrarZona(idZona: number): Observable<any> {
    return this.http.delete(this.apiUrl + '/' + idZona);
  }

  actualizarZona(idZona: number, zonaData: any): Observable<any> {
    return this.http.put(this.apiUrl + '/' + idZona, zonaData);
  }

  getCementerio(idZona: number): Observable<any> {
    return this.http.get(this.apiUrl + '/' + idZona + '/cementerio');
  }

}
