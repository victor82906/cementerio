import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class PagoService {
  private apiUrl = 'http://localhost:8081/pago';
  private apiFactura = 'http://localhost:8081/factura';

  constructor(private http: HttpClient) { }

  init(monto: number){
    return this.http.post(this.apiUrl + '/init', { monto: monto });
  }

  get(id: number){
    return this.http.get(this.apiUrl + '/' + id);
  }

  guardarFacturaConcesion(facturaData: any): Observable<any> {
    return this.http.post(this.apiFactura, + '/concesion' + facturaData);
  }

  guardarFacturaServicio(facturaData: any): Observable<any> {
    return this.http.post(this.apiFactura, + '/servicio' + facturaData);
  }

}
