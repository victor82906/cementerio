import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusquedaAyuntamientos } from './busqueda-ayuntamientos';

describe('BusquedaAyuntamientos', () => {
  let component: BusquedaAyuntamientos;
  let fixture: ComponentFixture<BusquedaAyuntamientos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BusquedaAyuntamientos]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BusquedaAyuntamientos);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
