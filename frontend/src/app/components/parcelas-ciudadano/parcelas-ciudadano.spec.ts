import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParcelasCiudadano } from './parcelas-ciudadano';

describe('ParcelasCiudadano', () => {
  let component: ParcelasCiudadano;
  let fixture: ComponentFixture<ParcelasCiudadano>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ParcelasCiudadano]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ParcelasCiudadano);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
