import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DifuntoService } from '../../services/difunto-service';
import { ChangeDetectorRef } from '@angular/core';
import { Cabecera } from '../cabecera/cabecera';
import { Footer } from '../footer/footer';
import { TarifaService } from '../../services/tarifa-service';
import { Cargando } from '../cargando/cargando';
import { ParcelaService } from '../../services/parcela-service';
import { ZonaService } from '../../services/zona-service';
import { ModalError } from '../modal-error/modal-error';
import { ModalConfirmar } from '../modal-confirmar/modal-confirmar';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FormGroup } from '@angular/forms';
import { ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
import { Validaciones } from '../../validators/validaciones';
import { Router } from '@angular/router';
import { ServicioService } from '../../services/servicio-service';

@Component({
  selector: 'app-comprar-servicio',
  imports: [Cabecera, Footer, Cargando, ModalError, ModalConfirmar, ReactiveFormsModule, CommonModule, FormsModule],
  templateUrl: './comprar-servicio.html',
  styleUrl: './comprar-servicio.css',
})
export class ComprarServicio {

  idParcela: number = 0;
  tarifas: any[] = [];
  difuntos: any[] = [];
  parcela: any;
  cementerio: any;
  zona: any;
  registroDifunto!: FormGroup;
  tarifaSeleccionada: any = null;
  @ViewChild("modalError") modalError!: ModalError;
  @ViewChild("modalConfirmar") modalConfirmar!: ModalConfirmar;
  idDifuntoABorrar: number | null = null;
  cargando: boolean = false;

  constructor(private router: Router, private route: ActivatedRoute, private difuntoService: DifuntoService, private cdr: ChangeDetectorRef, private tarifaService: TarifaService, private parcelaService: ParcelaService, private zonaService: ZonaService, private servicioService: ServicioService) { }

  ngOnInit(): void {
    this.registroDifunto = new FormGroup({
      dni: new FormControl('', [Validators.required, Validaciones.dniValido]),
      nombre: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$')]),
      apellidos: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$')]),
      fechaNacimiento: new FormControl('', [Validators.required, Validaciones.anterior(new Date())]),
      fechaFallecimiento: new FormControl('', [Validators.required, Validaciones.anterior(new Date())]),
      biografia: new FormControl('', [Validators.maxLength(300)])
    }, {
      validators: Validaciones.fallecimientoDespuesNacimiento
    });
    this.route.queryParams.subscribe(params => {
      this.idParcela = params['parcelaId'];
    });
    this.cargarDatos();
  }

  cargarDatos() {
    this.tarifaSeleccionada = null;
    this.cdr.markForCheck();
    this.cargando = true;
    this.parcelaService.getParcela(this.idParcela).subscribe({
      next: (parcela) => {
        this.parcela = parcela;
        this.difuntoService.getDifuntosParcela(this.idParcela).subscribe({
          next: (difuntos) => {
            this.difuntos = difuntos;
            this.cargaZonaCementerio();
          },
          error: (err) => {
            console.error(err);
            this.cargando = false;
            this.cdr.markForCheck();
          }
        });
      },
      error: (err) => {
        console.error(err);
        this.cargando = false;
        this.cdr.markForCheck();
      }
    });

  }

  cargaZonaCementerio() {
    this.parcelaService.getZona(this.idParcela).subscribe({
      next: (zona) => {
        this.zona = zona;
        this.zonaService.getCementerio(this.zona.id).subscribe({
          next: (cementerio) => {
            this.cementerio = cementerio;
            this.tarifaService.getTarifasCementerio(this.cementerio.id).subscribe({
              next: (tarifas) => {
                this.tarifas = tarifas;
                this.cargando = false;
                this.cdr.markForCheck();
              },
              error: (err) => {
                console.error(err);
                this.cargando = false;
                this.cdr.markForCheck();
              }
            });
          },
          error: (err) => {
            console.error(err);
            this.cargando = false;
            this.cdr.markForCheck();
          }
        });
      },
      error: (err) => {
        console.error(err);
        this.cargando = false;
        this.cdr.markForCheck();
      }
    });
  }

  inhumarDifunto(difuntoId: number) {
    this.idDifuntoABorrar = difuntoId;
    this.modalConfirmar.abrir();
  }

  confirmarBorrado() {
    if (this.idDifuntoABorrar !== null) {
      this.difuntoService.borrar(this.idDifuntoABorrar).subscribe({
        next: (response) => {
          this.cargarDatos();
          this.idDifuntoABorrar = null;
        },
        error: (err) => {
          console.error(err);
        }
      });
    }
  }

  pagarServicio() {
    if (this.esExhumacion()) {
      this.guardarDifunto();
    } else if (this.esInhumacion()) {
      return;
    } else {
      this.pagar();
    }
  }

  esExhumacion() {
    return this.tarifaSeleccionada?.tipoServicio.nombre == "Exhumacion";
  }

  esInhumacion() {
    return this.tarifaSeleccionada?.tipoServicio.nombre == "Inhumacion";
  }

  verCementerio() {
    this.router.navigate(['/cementerio'], { queryParams: { id: this.cementerio.id } });
  }

  guardarDifunto() {
    if (this.registroDifunto.valid) {
      const difunto = this.registroDifunto.value;
      difunto.dni = difunto.dni.toUpperCase();
      this.difuntoService.guardarDifunto(this.idParcela, difunto).subscribe({
        next: (response) => {
          this.modalError.abrirModal("Exito", "Difunto registrado correctamente", false);
          this.registroDifunto.reset();
          this.cargarDatos();
        },
        error: (err) => {
          this.modalError.abrirModal("Error", err.error, true);
          console.error(err);
        }
      });
    } else {
      this.registroDifunto.markAllAsTouched();
    }
  }

  pagar() {
    const servicio = {
      tipoServicio: this.tarifaSeleccionada.tipoServicio
    };
    this.servicioService.pagarServicio(this.idParcela, servicio).subscribe({
      next: (response) => {
        this.modalError.abrirModal("Exito", "Servicio pagado correctamente", false);
        this.cargarDatos();
      },
      error: (err) => {
        this.modalError.abrirModal("Error", err.error, true);
        console.error(err);
      }
    });

  }

}
