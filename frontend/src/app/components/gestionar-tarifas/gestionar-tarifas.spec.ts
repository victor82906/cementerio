import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionarTarifas } from './gestionar-tarifas';

describe('GestionarTarifas', () => {
  let component: GestionarTarifas;
  let fixture: ComponentFixture<GestionarTarifas>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GestionarTarifas]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestionarTarifas);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
