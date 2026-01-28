import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ServicioService {
  
  private urlServicio = 'http://localhost:8081/servicio';
  private urlTipoServicio = 'http://localhost:8081/tipo-servicio';

  constructor(private http: HttpClient) {}

  crearTipoServicio(tipoServicio: any): Observable<any> {
    return this.http.post(this.urlTipoServicio, tipoServicio);
  }

}
