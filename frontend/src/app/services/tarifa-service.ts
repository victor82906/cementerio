import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TarifaService {
  
  private apiUrl = 'http://localhost:8081/tarifa';
  private apiCementerio = 'http://localhost:8081/cementerio';

  constructor(private http: HttpClient) {}

  borrarTarifa(idTarifa: number): Observable<any> {
    return this.http.delete(this.apiUrl + '/' + idTarifa);
  }

  actualizarTarifa(idTarifa: number, tarifaData: any): Observable<any> {
    return this.http.put(this.apiUrl + '/' + idTarifa, tarifaData);
  }

  getTarifasCementerio(idCementerio: number): Observable<any> {
    return this.http.get(this.apiCementerio + "/" + idCementerio + "/tarifa");
  }

  crearTarifa(idCementerio: number, tarifaData: any): Observable<any> {
    return this.http.post(this.apiCementerio + "/" + idCementerio + "/tarifa", tarifaData);
  }

}
