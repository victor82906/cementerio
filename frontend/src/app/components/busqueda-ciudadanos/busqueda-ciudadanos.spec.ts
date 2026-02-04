import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusquedaCiudadanos } from './busqueda-ciudadanos';

describe('BusquedaCiudadanos', () => {
  let component: BusquedaCiudadanos;
  let fixture: ComponentFixture<BusquedaCiudadanos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BusquedaCiudadanos]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BusquedaCiudadanos);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
