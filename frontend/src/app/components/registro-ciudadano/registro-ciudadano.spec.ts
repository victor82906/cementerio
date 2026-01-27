import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroCiudadano } from './registro-ciudadano';

describe('RegistroCiudadano', () => {
  let component: RegistroCiudadano;
  let fixture: ComponentFixture<RegistroCiudadano>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistroCiudadano]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistroCiudadano);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
