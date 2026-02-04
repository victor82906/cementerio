import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class ConcesionService {

  private apiUrl = 'http://localhost:8081/concesion';
  private parcelaUrl = 'http://localhost:8081/parcela';
  private ciudadanoUrl = 'http://localhost:8081/ciudadano';

  constructor(private http: HttpClient) { }

  guardarConcesion(parcelaId: number, concesionData: any): Observable<any> {
    return this.http.post(this.parcelaUrl + '/' + parcelaId + '/concesion', concesionData);
  }

  getConcesionesCiudadano(ciudadanoId: number): Observable<any> {
    return this.http.get(this.ciudadanoUrl + '/' + ciudadanoId + '/concesion');
  }

  borrar(concesionId: number): Observable<any> {
    return this.http.delete(this.apiUrl + '/' + concesionId);
  }
  
}
