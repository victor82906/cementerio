import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FacturaService {

  private apiUrl = 'http://localhost:8081/factura';
  private apiAyuntamiento = 'http://localhost:8081/ayuntamiento';
  private apiCiudadano = 'http://localhost:8081/ciudadano';

  constructor(private http: HttpClient) {}

  getAyuntamiento(id: number): Observable<any> {
    return this.http.get(this.apiAyuntamiento + '/' + id + '/factura');
  }

  getCiudadano(id: number): Observable<any> {
    return this.http.get(this.apiCiudadano + '/' + id + '/factura');
  }

  descarga(id: number): Observable<any> {
    return this.http.get(this.apiUrl + '/' + id + '/descargar', { responseType: 'blob' });
  }

  descargaFactura(id: number){
    this.descarga(id).subscribe((blob) =>{
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'factura' + id + '.pdf';
      a.click();
      window.URL.revokeObjectURL(url);
    });
  }
  
}
