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

    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: '**', redirectTo: 'home' }
];
