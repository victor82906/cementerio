import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { Home } from './components/home/home';
import { RegistroAyuntamiento } from './components/registro-ayuntamiento/registro-ayuntamiento';
import { CementerioBusqueda } from './components/cementerio-busqueda/cementerio-busqueda';
import { Administrador } from './components/administrador/administrador';
import { Ayuntamiento } from './components/ayuntamiento/ayuntamiento';
import { Ciudadano } from './components/ciudadano/ciudadano';
import { rolGuard } from './guards/rol-guard';
import { RegistroCiudadano } from './components/registro-ciudadano/registro-ciudadano';
import { SubirFotoComponent } from './components/subir-foto/subir-foto';
import { EditarCiudadano } from './components/editar-ciudadano/editar-ciudadano';
import { CambiarContrasena } from './components/cambiar-contrasena/cambiar-contrasena';
import { EditarAyuntamiento } from './components/editar-ayuntamiento/editar-ayuntamiento';
import { CrearTipoServicio } from './components/crear-tipo-servicio/crear-tipo-servicio';
import { RegistroCementerio } from './components/registro-cementerio/registro-cementerio';
import { CementeriosAyuntamiento } from './components/cementerios-ayuntamiento/cementerios-ayuntamiento';
import { BusquedaCiudadanos } from './components/busqueda-ciudadanos/busqueda-ciudadanos';
import { BusquedaAyuntamientos } from './components/busqueda-ayuntamientos/busqueda-ayuntamientos';
import { Cementerio } from './components/cementerio/cementerio';
import { GestionarTarifas } from './components/gestionar-tarifas/gestionar-tarifas';
import { GestionZonas } from './components/gestion-zonas/gestion-zonas';
import { ComprarConcesion } from './components/comprar-concesion/comprar-concesion';
import { ParcelasCiudadano } from './components/parcelas-ciudadano/parcelas-ciudadano';
import { ComprarServicio } from './components/comprar-servicio/comprar-servicio';
import { DifuntosCiudadano } from './components/difuntos-ciudadano/difuntos-ciudadano';
import { ServiciosAyuntamiento } from './components/servicios-ayuntamiento/servicios-ayuntamiento';

export const routes: Routes = [
    { path: 'login', component: Login },
    { path: 'home', component: Home },
    { path: 'registro-ayuntamiento', component: RegistroAyuntamiento, canActivate: [rolGuard], data: {roles:['ADMIN']} },
    { path: 'registro-ciudadano', component: RegistroCiudadano },
    { path: 'busqueda-cementerio', component: CementerioBusqueda },
    { path: 'administrador', component: Administrador, canActivate: [rolGuard], data: {roles:['ADMIN']} },
    { path: 'ayuntamiento', component: Ayuntamiento, canActivate: [rolGuard], data: {roles:['AYUNTAMIENTO']} },
    { path: 'ciudadano', component: Ciudadano, canActivate: [rolGuard], data: {roles:['CIUDADANO']}},
    { path: 'subir-foto', component: SubirFotoComponent, canActivate: [rolGuard], data: {roles:['ADMIN', 'AYUNTAMIENTO', 'CIUDADANO']} },
    { path: 'editar-ciudadano', component: EditarCiudadano, canActivate: [rolGuard], data: {roles:['ADMIN', 'CIUDADANO']} },
    { path: 'editar-ayuntamiento', component: EditarAyuntamiento, canActivate: [rolGuard], data: {roles:['ADMIN', 'AYUNTAMIENTO']} },
    { path: 'cambiar-contrasena', component: CambiarContrasena, canActivate: [rolGuard], data: {roles:['ADMIN', 'AYUNTAMIENTO', 'CIUDADANO']} },
    { path: 'crear-tipo-servicio', component: CrearTipoServicio, canActivate: [rolGuard], data: {roles:['ADMIN', 'AYUNTAMIENTO']}},
    { path: 'registro-cementerio', component: RegistroCementerio, canActivate: [rolGuard], data: {roles:['ADMIN', 'AYUNTAMIENTO']}},
    { path: 'cementerios-ayuntamiento', component: CementeriosAyuntamiento, canActivate: [rolGuard], data: {roles:['ADMIN', 'AYUNTAMIENTO']}},
    { path: 'busqueda-ciudadanos', component: BusquedaCiudadanos, canActivate: [rolGuard], data: {roles:['ADMIN']}},
    { path: 'busqueda-ayuntamientos', component: BusquedaAyuntamientos, canActivate: [rolGuard], data: {roles:['ADMIN']}},
    { path: 'cementerio', component: Cementerio },
    { path: 'gestionar-tarifas', component: GestionarTarifas, canActivate: [rolGuard], data: {roles:['ADMIN', 'AYUNTAMIENTO']}},
    { path: 'gestion-zonas', component: GestionZonas, canActivate: [rolGuard], data: {roles:['ADMIN']}},
    { path: 'comprar-concesion', component: ComprarConcesion, canActivate: [rolGuard], data: {roles:['CIUDADANO']}},
    { path: 'parcelas-ciudadano', component: ParcelasCiudadano, canActivate: [rolGuard], data: {roles:['ADMIN', 'CIUDADANO']}},
    { path: 'comprar-servicio', component: ComprarServicio, canActivate: [rolGuard], data: {roles:['ADMIN', 'CIUDADANO']}},
    { path: 'difuntos-ciudadano', component: DifuntosCiudadano, canActivate: [rolGuard], data: {roles:['ADMIN', 'CIUDADANO']}},
    { path: 'servicios-ayuntamiento', component: ServiciosAyuntamiento, canActivate: [rolGuard], data: {roles:['ADMIN', 'AYUNTAMIENTO']}},

    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: '**', redirectTo: 'home' }
];
