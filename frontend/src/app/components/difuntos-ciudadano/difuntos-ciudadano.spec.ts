import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DifuntosCiudadano } from './difuntos-ciudadano';

describe('DifuntosCiudadano', () => {
  let component: DifuntosCiudadano;
  let fixture: ComponentFixture<DifuntosCiudadano>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DifuntosCiudadano]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DifuntosCiudadano);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
