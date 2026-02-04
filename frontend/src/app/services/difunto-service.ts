import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DifuntoService {

  private apiUrl = 'http://localhost:8081/difunto';
  private apiParcela = 'http://localhost:8081/parcela';
  private apiCiudadano = 'http://localhost:8081/ciudadano';

  constructor(private http: HttpClient){}
  
  getDifuntosParcela(parcelaId: number): Observable<any> {
    return this.http.get(this.apiParcela + '/' + parcelaId + '/difunto');
  }

  borrar(difuntoId: number): Observable<any> {
    return this.http.delete(this.apiUrl + '/' + difuntoId);
  }

  guardarDifunto(parcelaId: number, difuntoData: any): Observable<any> {
    return this.http.post(this.apiParcela + '/' + parcelaId + '/difunto', difuntoData);
  }

  getParcelaDifunto(id: number): Observable<any> {
    return this.http.get(this.apiUrl + '/' + id +'/parcela');
  }

  getDifuntosCiudadano(ciudadanoId: number): Observable<any> {
    return this.http.get(this.apiCiudadano + '/' + ciudadanoId + '/difunto');
  }

}
