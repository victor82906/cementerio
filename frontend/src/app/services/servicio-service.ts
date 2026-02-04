import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ServicioService {
  
  private urlServicio = 'http://localhost:8081/servicio';
  private urlTipoServicio = 'http://localhost:8081/tipo-servicio';
  private urlParcela = 'http://localhost:8081/parcela';
  private urlAyuntamiento = 'http://localhost:8081/ayuntamiento';

  constructor(private http: HttpClient) {}

  crearTipoServicio(tipoServicio: any): Observable<any> {
    return this.http.post(this.urlTipoServicio, tipoServicio);
  }

  getAllTipoServicios(): Observable<any> {
    return this.http.get(this.urlTipoServicio);
  }

  pagarServicio(idParcela: number, servicio: any): Observable<any> {
    return this.http.post(this.urlParcela + '/' + idParcela + '/servicio', servicio);
  }

  getServiciosAyuntamiento(idAyuntamiento: number): Observable<any> {
    return this.http.get(this.urlAyuntamiento + '/' + idAyuntamiento + '/servicio');
  }

  marcarRealizado(idServicio: number): Observable<any> {
    return this.http.put(this.urlServicio + '/' + idServicio + '/realizado', null);
  }

}
