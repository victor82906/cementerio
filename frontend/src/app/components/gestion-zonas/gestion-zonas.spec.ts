import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionZonas } from './gestion-zonas';

describe('GestionZonas', () => {
  let component: GestionZonas;
  let fixture: ComponentFixture<GestionZonas>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GestionZonas]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestionZonas);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
