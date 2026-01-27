import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { Home } from './components/home/home';
import { RegistroAyuntamiento } from './components/registro-ayuntamiento/registro-ayuntamiento';
import { CementerioBusqueda } from './components/cementerio-busqueda/cementerio-busqueda';
import { Administrador } from './components/administrador/administrador';
import { Ayuntamiento } from './components/registro-ayuntamiento/ayuntamiento';
import { Ciudadano } from './components/ciudadano/ciudadano';
import { rolGuard } from './guards/rol-guard';
import { RegistroCiudadano } from './components/registro-ciudadano/registro-ciudadano';

export const routes: Routes = [
    { path: 'login', component: Login },
    { path: 'home', component: Home },
    { path: 'registro-ayuntamiento', component: RegistroAyuntamiento },
    { path: 'registro-ciudadano', component: RegistroCiudadano },
    { path: 'busqueda-cementerio', component: CementerioBusqueda },
    { path: 'administrador', component: Administrador, canActivate: [rolGuard], data: {roles:['ADMIN']} },
    { path: 'ayuntamiento', component: Ayuntamiento },
    { path: 'ciudadano', component: Ciudadano },

    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: '**', redirectTo: 'home' }
];
