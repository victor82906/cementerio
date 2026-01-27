export enum Rol {
    ADMIN="ADMIN", 
    AYUNTAMIENTO="AYUNTAMIENTO", 
    CIUDADANO="CIUDADANO"
};

export type Usuario = {
    nombre:string,
    email:string,
    rol:Rol;
}